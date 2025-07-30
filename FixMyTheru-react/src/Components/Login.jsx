import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

const Login = () => {
  const [formData, setFormData] = useState({
    username: '',
    password: ''
  });
  const [error, setError] = useState('');
  const [success, setSuccess] = useState('');

  const navigate=useNavigate();
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
  try {
    const response = await axios.post("https://fixmytheru.onrender.com/api/auth/login", formData);
    const { token, id, role } = response.data;

    localStorage.setItem('role', role);
    localStorage.setItem('token', token);
    localStorage.setItem('id', id);

    setSuccess("Login Successful");
    console.log(role);
  
    if (role === 'ADMIN') navigate('/admin');
    else if (role === 'USER') navigate('/home');
    else navigate('/employee');
    window.dispatchEvent(new Event('storageChange')); 

  } catch (error) {
    setError("Invalid credentials");
  }
};


  return (
    <div className="container-fluid p-0 m-0">
      <div className="row w-100 m-0 vh-100">
        <div className="col-md-6 d-none d-md-block p-0 ">
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
            <h3 className="text-center mb-4">Login</h3>
            <div className="mb-3">
              <label htmlFor="username" className="form-label">Username</label>
              <input
                type="text"
                className="form-control"
                id="username"
                value={formData.username}
                onChange={handleChange}
                placeholder="Enter your username"
                required
              />
            </div>
            <div className="mb-4">
              <label htmlFor="password" className="form-label">Password</label>
              <input
                type="password"
                className="form-control"
                id="password"
                value={formData.password}
                onChange={handleChange}
                placeholder="Enter your password"
                required
              />
            </div>
             {success && (
              <div className="mb-3 text-success text-center">{success}</div>
            )}
            {error && (
              <div className="mb-3 text-danger text-center">{error}</div>
            )}
            <button type="submit" className="btn btn-primary w-100">
              Login
            </button>
          </form>
        </div>
      </div>
    </div>
  );
};

export default Login;
