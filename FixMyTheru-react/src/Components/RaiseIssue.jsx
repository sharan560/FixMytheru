import React, { useState } from 'react';
import axios from 'axios';

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
    <div style={{ padding: '20px', maxWidth: '500px', margin: 'auto' }}>
      <h2>Raise an Issue</h2>
      <form onSubmit={handleSubmit}>
        <input
          type="text"
          name="issueName"
          placeholder="Issue Name"
          value={formData.issueName}
          onChange={handleChange}
          required
        /><br />

        <input
          type="text"
          name="issueDescription"
          placeholder="Issue Description"
          value={formData.issueDescription}
          onChange={handleChange}
          required
        /><br />

        <input
          type="date"
          name="issueDate"
          value={formData.issueDate}
          onChange={handleChange}
          required
        /><br />

        <input
          type="time"
          name="issueTime"
          value={formData.issueTime}
          onChange={handleChange}
          required
        /><br />

        <input
          type="text"
          name="issueLocation"
          placeholder="Location"
          value={formData.issueLocation}
          onChange={handleChange}
          required
        /><br />

        <input
          type="text"
          name="issueType"
          placeholder="Type (e.g. Road)"
          value={formData.issueType}
          onChange={handleChange}
          required
        /><br />

        <input
          type="file"
          multiple
          accept="image/*"
          onChange={handleImageChange}
        /><br /><br />

        <button type="submit">Submit Issue</button>
      </form>
    </div>
  );
};

export default RaiseIssue;
