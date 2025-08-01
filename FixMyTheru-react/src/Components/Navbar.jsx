import React, { useEffect, useState } from 'react';

import logo from '../assets/logo.jpg';
const Navbar = () => {
  const [token, setToken] = useState(localStorage.getItem('token'));
  const [role, setRole] = useState(localStorage.getItem('role'));

  
  useEffect(() => {
    const handleStorageChange = () => {
      setToken(localStorage.getItem('token'));
      setRole(localStorage.getItem('role'));
    };

    window.addEventListener('storageChange', handleStorageChange);
    return () => {
      window.removeEventListener('storageChange', handleStorageChange);
    };
  }, []);

  const handleLogout = () => {
    localStorage.removeItem('token');
    localStorage.removeItem('role');
    localStorage.removeItem('id');
    setToken(null);
    setRole(null);
    window.dispatchEvent(new Event('storageChange'));
    window.location.href = '/'; 
  };

    return (
      <nav className="navbar navbar-expand-lg" style={{ backgroundColor: '#1565C0' }}>
        <div className="container-fluid d-flex justify-content-between align-items-center px-4">
          <a className="navbar-brand d-flex align-items-center text-white" href="/" style={{ fontWeight: 'bold' }}>
            <img src={logo} alt="Logo of FixMyTheru" width="40" height="40" className="me-2" />
          </a>

          <div className="d-flex gap-3">
            {!token && (
              <>
                <a className="btn btn-outline-light" href="/">Login</a>
                <a className="btn btn-light" style={{ backgroundColor: '#F57C00', color: 'white' }} href="/register">Register</a>
              </>
            )}

            {token && (
              <>
                {role === 'ADMIN' && (
                  <>
                  <a className="btn btn-outline-light" href="/admin">Home</a>
                  <a className="btn btn-outline-light" href="/empform">Add Employee</a>
                  </>
                
                )}
                {role === 'MAINTANENCE' && (
                  <>
                    <a className="btn btn-outline-light" href="/employee">Home</a>
                  </>
                )}
                {role === 'USER' && (
                  <>
                  <a className="btn btn-outline-light" href="/home">Home</a>
                  <a className="btn btn-outline-light" href="/raiseissue">FileComplaint</a>
                  </>

                )}
                <a className="btn btn-outline-light" href="/profile">Profile</a>
                <button className="btn btn-danger" onClick={handleLogout}>Logout</button>
              </>
            )}
          </div>
        </div>
      </nav>
    );
  };

  export default Navbar;
