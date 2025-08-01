import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { useParams } from 'react-router-dom';

const WorkUpdateForm = () => {
  const { issueId } = useParams(); // assume this is passed from the route
  const [formData, setFormData] = useState({
    workDescription: '',
    workDate: '',
    workTime: '',
    mainteneceId: '',
    images: [],
  });

  const [issueName, setIssueName] = useState('');
  
  useEffect(() => {
    const token = localStorage.getItem('token');
    if (issueId) {
      axios.get(`https://fixmytheru.onrender.com/issues/${issueId}`, {
        headers: { Authorization: `Bearer ${token}` }
      })
      .then(res => {
        setIssueName(res.data.issueName);
      })
      .catch(err => {
        console.error("Error fetching issue details", err);
      });
    }
  }, [issueId]);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData(prev => ({ ...prev, [name]: value }));
  };

  const handleFileChange = (e) => {
    setFormData(prev => ({ ...prev, images: [...e.target.files] }));
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    const token = localStorage.getItem('token');

    const form = new FormData();

    const workupdateObj = {
      workDescription: formData.workDescription,
      workDate: formData.workDate,
      workTime: formData.workTime,
      issues: { issueId: parseInt(issueId) },
      maintaience: { id: parseInt(formData.mainteneceId) }
    };

    form.append("workupdate", new Blob([JSON.stringify(workupdateObj)], { type: "application/json" }));

    for (let i = 0; i < formData.images.length; i++) {
      form.append("file", formData.images[i]);
    }

    axios.post("https://fixmytheru.onrender.com/update/add", form, {
      headers: {
        Authorization: `Bearer ${token}`,
        "Content-Type": "multipart/form-data",
      }
    })
      .then(res => alert("Work update submitted successfully!"))
      .catch(err => alert("Error submitting work update."));
  };

  return (
    <div className="container mt-5 mb-5">
      <h2 className="text-center mb-4" style={{ color: '#1565C0' }}>
        Submit Work Update <span style={{ color: '#F57C00' }}>Theru</span>
      </h2>

      <form onSubmit={handleSubmit} className="p-4 shadow rounded" style={{ backgroundColor: '#fdfdfd' }}>
        <div className="mb-3">
          <label className="form-label fw-bold">Issue Name</label>
          <input
            type="text"
            className="form-control"
          />
        </div>

        <div className="mb-3">
          <label className="form-label fw-bold">Work Description</label>
          <textarea
            name="workDescription"
            value={formData.workDescription}
            onChange={handleChange}
            className="form-control"
            rows="3"
            placeholder="Describe the work done"
            required
          ></textarea>
        </div>

        <div className="row">
          <div className="col-md-6 mb-3">
            <label className="form-label fw-bold">Work Date</label>
            <input
              type="date"
              name="workDate"
              value={formData.workDate}
              onChange={handleChange}
              className="form-control"
              required
            />
          </div>

          <div className="col-md-6 mb-3">
            <label className="form-label fw-bold">Work Time</label>
            <input
              type="time"
              name="workTime"
              value={formData.workTime}
              onChange={handleChange}
              className="form-control"
              required
            />
          </div>
        </div>

        <div className="mb-3">
          <label className="form-label fw-bold">Maintenance User ID</label>
          <input
            type="number"
            name="mainteneceId"
            value={formData.mainteneceId}
            onChange={handleChange}
            className="form-control"
            placeholder="Enter your user ID"
            required
          />
        </div>

        <div className="mb-4">
          <label className="form-label fw-bold">Upload Images</label>
          <input
            type="file"
            name="images"
            multiple
            onChange={handleFileChange}
            className="form-control"
            accept="image/*"
          />
        </div>

        <div className="text-center">
          <button type="submit" className="btn btn-primary px-4 py-2" style={{ backgroundColor: '#1565C0', border: 'none' }}>
            Submit Update
          </button>
        </div>
      </form>
    </div>
  );
};

export default WorkUpdateForm;
