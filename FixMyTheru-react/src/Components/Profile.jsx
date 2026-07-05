import React, { useEffect, useState } from 'react';
import axios from 'axios';

const Profile = () => {
  const userId = Number(localStorage.getItem("id"));
  const token = localStorage.getItem("token"); 
  const [user, setUser] = useState({});
  const [issues, setIssues] = useState([]);

  const headers = {
    headers: {
      Authorization: `Bearer ${token}`,
    },
  };

  useEffect(() => {
    axios.get(`https://fixmytheru.onrender.com/users/${userId}`, headers)
      .then(res => {
        setUser(res.data);
      })
      .catch(err => console.error("User fetch error:", err));

    axios.get(`https://fixmytheru.onrender.com/Issue/getIssue/${userId}`, headers)
      .then(res => {
        setIssues(res.data);
      })
      .catch(err => console.error("Issue fetch error:", err));
  }, [userId]);

  return (
    <div className="container mt-4">
      <h2 className="mb-3">👤 User Profile</h2>
      <div className="card p-3 shadow-sm mb-4">
        <h5>Name: {user.name}</h5>
        <p>Email: {user.email}</p>
        <p>Address: {user.address}</p>
      </div>

      <h3 className="mb-3">📋 Issues Raised</h3>
      {issues.length === 0 ? (
        <p>No issues raised yet.</p>
      ) : (
        issues.map(issue => (
          <div className="card p-3 mb-3 shadow-sm" key={issue.id}>
            <h5>{issue.issuename}</h5>
            <p>{issue.issuedescription}</p>
            <p><strong>Type:</strong> {issue.issueType}</p>
            <p> <strong>Status:</strong>{' '}
                  <span
                    className={`badge ${
                      issue.issuestatus === 'COMPLETED'
                        ? 'bg-success'
                        : issue.issuestatus === 'NOT_STARTED'
                        ? 'bg-danger'
                        : 'bg-warning'
                    }`}
                  >
                    {issue.issuestatus}
                  </span></p>
            <p><strong>Date:</strong> {new Date(issue.issuedate).toLocaleDateString()}</p>
            <p><strong>Time:</strong> {new Date(issue.issuetime).toLocaleTimeString()}</p>
          </div>
        ))
      )}
    </div>
  );
};

export default Profile;
