import React, { useState } from 'react';
import axios from 'axios';
import { Navigate } from 'react-router-dom';


const Register = () => {
  const [formData, setFormData] = useState({
    name: '',
    email: '',
    username: '',
    password: '',
    address: ''
  });

  const [error, setError] = useState('');
  const [success, setSuccess] = useState('');

  const handleChange = (e) => {
    const { id, value } = e.target;
    setFormData((prev) => ({
      ...prev,
      [id]: value
    }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError('');
    setSuccess('');
    try {
      const response = await axios.post("https://fixmytheru.onrender.com/api/auth/register", formData);
      navigate('/');
      setSuccess("Registration Successful");
    } catch (error) {
      setError("Registration failed");
    }
  };

  return (
    <div className="container-fluid p-0 m-0">
      <div className="row w-100 m-0 vh-100">
        <div className="col-md-6 d-none d-md-block p-0">
          <img
            src="/src/assets/logo.png"
            alt="Logo"
            className="img-fluid w-100"
            style={{ height: '100vh', objectFit: 'cover' }}
          />
        </div>
        <div className="col-12 col-md-6 d-flex justify-content-center align-items-center bg-light">
          <form
            className="p-4 shadow rounded bg-white"
            style={{ width: '100%', maxWidth: '400px' }}
            onSubmit={handleSubmit}
          >
            <h3 className="text-center mb-4">Register</h3>

            <div className="mb-3">
              <label htmlFor="name" className="form-label">Full Name</label>
              <input
                type="text"
                className="form-control"
                id="name"
                value={formData.name}
                onChange={handleChange}
                placeholder="Enter your name"
                required
              />
            </div>

            <div className="mb-3">
              <label htmlFor="email" className="form-label">Email</label>
              <input
                type="email"
                className="form-control"
                id="email"
                value={formData.email}
                onChange={handleChange}
                placeholder="Enter your email"
                required
              />
            </div>

            <div className="mb-3">
              <label htmlFor="username" className="form-label">Username</label>
              <input
                type="text"
                className="form-control"
                id="username"
                value={formData.username}
                onChange={handleChange}
                placeholder="Choose a username"
                required
              />
            </div>

            <div className="mb-3">
              <label htmlFor="password" className="form-label">Password</label>
              <input
                type="password"
                className="form-control"
                id="password"
                value={formData.password}
                onChange={handleChange}
                placeholder="Enter a password"
                required
              />
            </div>

            <div className="mb-4">
              <label htmlFor="address" className="form-label">Address</label>
              <textarea
                className="form-control"
                id="address"
                value={formData.address}
                onChange={handleChange}
                placeholder="Enter your address"
                rows="2"
                required
              />
            </div>

            {success && <div className="mb-3 text-success text-center">{success}</div>}
            {error && <div className="mb-3 text-danger text-center">{error}</div>}

            <button type="submit" className="btn btn-primary w-100">Register</button>
          </form>
        </div>
      </div>
    </div>
  );
};

export default Register;
