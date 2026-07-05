import React, { useState } from 'react';
import axios from 'axios';
import 'bootstrap/dist/css/bootstrap.min.css';

const EmployeeForm = () => {
  const [formData, setFormData] = useState({
    name: '',
    email: '',
    profileimage: null,
    password: '',
    username: '',
    role: '',
    address: ''
  });

  const [success, setSuccess] = useState('');
  const [error, setError] = useState('');

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const response = await axios.post(
        'https://fixmytheru.onrender.com/api/auth/register',
        formData
      );

      setSuccess('Registration Successful');
      setError('');
      console.log(response.data);
    } catch (err) {
      setError('Registration Failed');
      setSuccess('');
      console.error(err);
    }
  };

  return (
    <div className="container my-5 p-4 shadow rounded" style={{ backgroundColor: '#f8f9fa' }}>
      <h2 className="text-center mb-4" style={{ color: '#F57C00' }}>Employee Registration</h2>

      {success && <div className="alert alert-success">{success}</div>}
      {error && <div className="alert alert-danger">{error}</div>}

      <form onSubmit={handleSubmit}>
        <div className="row mb-3">
          <div className="col-md-6">
            <label className="form-label">Name</label>
            <input type="text" className="form-control" name="name" value={formData.name} onChange={handleChange} required />
          </div>
          <div className="col-md-6">
            <label className="form-label">Username</label>
            <input type="text" className="form-control" name="username" value={formData.username} onChange={handleChange} required />
          </div>
        </div>

        <div className="mb-3">
          <label className="form-label">Email</label>
          <input type="email" className="form-control" name="email" value={formData.email} onChange={handleChange} required />
        </div>

        <div className="mb-3">
          <label className="form-label">Password</label>
          <input type="password" className="form-control" name="password" value={formData.password} onChange={handleChange} required />
        </div>

        <div className="row mb-3">
          <div className="col-md-6">
            <label className="form-label">Role</label>
            <select className="form-select" name="role" value={formData.role} onChange={handleChange} required>
              <option value="">Select Role</option>
              <option value="USER">ROLE-USER</option>
              <option value="MAINTANENCE">ROLE-MAINTANENCE</option>
              <option value="ADMIN">ROLE-ADMIN</option>
            </select>
          </div>
          <div className="col-md-6">
            <label className="form-label">Address</label>
            <input type="text" className="form-control" name="address" value={formData.address} onChange={handleChange} required />
          </div>
        </div>

        <button type="submit" className="btn" style={{ backgroundColor: '#1565C0', color: '#fff' }}>
          Register
        </button>
      </form>
    </div>
  );
};

export default EmployeeForm;
