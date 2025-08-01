import React, { useState } from 'react';
import axios from 'axios';

const WorkUpdateForm = () => {
  const token = localStorage.getItem('token');
  const userId = localStorage.getItem('id');
  const issueId = localStorage.getItem('issueid');

  const [formData, setFormData] = useState({
    workDescription: '',
    workDate: '',
    workTime: ''
  });

  const [images, setImages] = useState([]);

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleImageChange = (e) => {
    setImages(e.target.files);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (!userId || !issueId) {
      alert("User ID or Issue ID missing. Please try again.");
      return;
    }

    const workPayload = {
      ...formData,
      workTime: formData.workTime + ':00',
      userid: parseInt(userId),
      issueid: parseInt(issueId)
    };

    const data = new FormData();
    data.append('workupdate', new Blob([JSON.stringify(workPayload)], { type: 'application/json' }));

    for (let i = 0; i < images.length; i++) {
      data.append('file', images[i]);
    }

    try {
      const response = await axios.post('https://fixmytheru.onrender.com/update/add', data, {
        headers: {
          'Content-Type': 'multipart/form-data',
          'Authorization': `Bearer ${token}`
        }
      });
      alert("Work update submitted successfully.");
    } catch (error) {
      console.error(error);
      alert("Failed to submit work update.");
    }
  };

  return (
    <div className="container mt-5" style={{ maxWidth: '600px' }}>
      <div className="card shadow">
        <div className="card-header text-white" style={{ backgroundColor: '#1565C0' }}>
          <h4 className="mb-0">Submit Work Update</h4>
        </div>
        <div className="card-body" style={{ backgroundColor: '#f8f9fa' }}>
          <form onSubmit={handleSubmit}>
            <div className="mb-3">
              <label className="form-label">Work Description</label>
              <input
                type="text"
                name="workDescription"
                className="form-control"
                value={formData.workDescription}
                onChange={handleChange}
                required
              />
            </div>

            <div className="mb-3">
              <label className="form-label">Work Date</label>
              <input
                type="date"
                name="workDate"
                className="form-control"
                value={formData.workDate}
                onChange={handleChange}
                required
              />
            </div>

            <div className="mb-3">
              <label className="form-label">Work Time</label>
              <input
                type="time"
                name="workTime"
                className="form-control"
                value={formData.workTime}
                onChange={handleChange}
                required
              />
            </div>

            <div className="mb-3">
              <label className="form-label">Upload Images</label>
              <input
                type="file"
                className="form-control"
                multiple
                accept="image/*"
                onChange={handleImageChange}
              />
            </div>

            <button
              type="submit"
              className="btn"
              style={{ backgroundColor: '#F57C00', color: 'white' }}
            >
              Submit Work Update
            </button>
          </form>
        </div>
      </div>
    </div>
  );
};

export default WorkUpdateForm;
