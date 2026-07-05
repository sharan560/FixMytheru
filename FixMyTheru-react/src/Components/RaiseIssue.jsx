import React, { useState } from 'react';
import axios from 'axios';
import 'bootstrap/dist/css/bootstrap.min.css';

const RaiseIssue = () => {
  const token = localStorage.getItem('token');
  const userId = localStorage.getItem('id');

  const [formData, setFormData] = useState({
    issueName: '',
    issueDescription: '',
    issueDate: '',
    issueTime: '',
    issueLocation: '',
    issueType: ''
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

    if (!userId) {
      alert("User ID not found. Please login again.");
      return;
    }

    const issuePayload = {
      ...formData,
      issueTime: formData.issueTime + ':00',
      userId: parseInt(userId)
    };

    const data = new FormData();
    data.append('issue', new Blob([JSON.stringify(issuePayload)], { type: 'application/json' }));

    for (let i = 0; i < images.length; i++) {
      data.append('images', images[i]);
    }

    try {
      const response = await axios.post('https://fixmytheru.onrender.com/Issue/addIssue', data, {
        headers: {
          'Content-Type': 'multipart/form-data',
          'Authorization': `Bearer ${token}`
        }
      });
      alert("Issue submitted successfully.");
     
    } catch (error) {
      console.error(error);
      alert("Failed to raise issue.");
    }
  };

  return (
    <div className="container py-5">
      <div
        className="mx-auto shadow-sm rounded-4 p-4"
        style={{ maxWidth: 600, backgroundColor: '#f8f9fa' }}
      >
        <h2 className="mb-4" style={{ color: '#1565C0', fontWeight: 700 }}>
          Raise an Issue
        </h2>
        <form onSubmit={handleSubmit}>
          <div className="mb-3">
            <label htmlFor="issueName" className="form-label fw-semibold">
              Issue Name <span className="text-danger">*</span>
            </label>
            <input
              id="issueName"
              type="text"
              name="issueName"
              className="form-control"
              placeholder="Issue Name"
              value={formData.issueName}
              onChange={handleChange}
              required
            />
          </div>

          <div className="mb-3">
            <label htmlFor="issueDescription" className="form-label fw-semibold">
              Description <span className="text-danger">*</span>
            </label>
            <textarea
              id="issueDescription"
              name="issueDescription"
              className="form-control"
              placeholder="Issue Description"
              value={formData.issueDescription}
              onChange={handleChange}
              required
              rows={3}
            />
          </div>

          <div className="row g-3 mb-3">
            <div className="col-md-6">
              <label htmlFor="issueDate" className="form-label fw-semibold">
                Date <span className="text-danger">*</span>
              </label>
              <input
                id="issueDate"
                type="date"
                name="issueDate"
                className="form-control"
                value={formData.issueDate}
                onChange={handleChange}
                required
              />
            </div>
            <div className="col-md-6">
              <label htmlFor="issueTime" className="form-label fw-semibold">
                Time <span className="text-danger">*</span>
              </label>
              <input
                id="issueTime"
                type="time"
                name="issueTime"
                className="form-control"
                value={formData.issueTime}
                onChange={handleChange}
                required
              />
            </div>
          </div>

          <div className="mb-3">
            <label htmlFor="issueLocation" className="form-label fw-semibold">
              Location <span className="text-danger">*</span>
            </label>
            <input
              id="issueLocation"
              type="text"
              name="issueLocation"
              className="form-control"
              placeholder="Location"
              value={formData.issueLocation}
              onChange={handleChange}
              required
            />
          </div>

          <div className="mb-3">
            <label htmlFor="issueType" className="form-label fw-semibold">
              Issue Type <span className="text-danger">*</span>
            </label>
            <input
              id="issueType"
              type="text"
              name="issueType"
              className="form-control"
              placeholder="Type (e.g. Road)"
              value={formData.issueType}
              onChange={handleChange}
              required
            />
          </div>

          <div className="mb-4">
            <label htmlFor="images" className="form-label fw-semibold">
              Upload Images
            </label>
            <input
              id="images"
              type="file"
              multiple
              accept="image/*"
              className="form-control"
              onChange={handleImageChange}
            />
            <div className="form-text">You can upload multiple photos (optional).</div>
          </div>

          <button
            type="submit"
            className="btn w-100"
            style={{
              backgroundColor: '#F57C00',
              color: '#FFFFFF',
              fontWeight: 600,
              borderRadius: '0.5rem'
            }}
          >
            Submit Issue
          </button>
        </form>
      </div>
    </div>
  );
};

export default RaiseIssue;
