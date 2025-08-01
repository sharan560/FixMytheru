import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';


const Employee = () => {
  const [issues, setIssues] = useState([]);
  const empId = localStorage.getItem("id");
  const token = localStorage.getItem("token");
  const navigate = useNavigate();

  const headers = {
    headers: {
      Authorization: `Bearer ${token}`,
    },
  };

  useEffect(() => {
    if (!empId) return;
    axios
      .get(`https://fixmytheru.onrender.com/Issue/getIssue/employee/${empId}`, headers)
      .then((res) => {
        setIssues(res.data);
      })
      .catch((err) => {
        console.error("Fetch error:", err);
      });
  }, [empId]);

  const handleUpdate = (issueid) => {
    localStorage.setItem("issueid", issueid);

   navigate(`/update`);
  };

  return (
    <div style={{ backgroundColor: '#F9F9F9', minHeight: '100vh', padding: '2rem' }}>
      <div className="container">
        <h2 className="mb-4 text-center" style={{ color: '#1565C0', fontWeight: 'bold' }}>
          🛠 FixMy<span style={{ color: '#F57C00' }}>Theru</span> - Assigned Issues
        </h2>

        {issues.length === 0 ? (
          <p style={{ color: '#777', textAlign: 'center' }}>No issues assigned.</p>
        ) : (
          <div className="row">
            {issues.map((issue) => (
              <div key={issue.id} className="col-md-4 mb-4">
                <div className="card h-100 shadow" style={{ borderRadius: '1rem', border: 'none' }}>
                  <div className="card-body d-flex flex-column justify-content-between">
                    <div>
                      <h5 className="card-title" style={{ color: '#1565C0', fontWeight: 'bold' }}>{issue.issuename}</h5>
                      <p className="card-text" style={{ color: '#333' }}>{issue.issuedescription}</p>
                      <p><strong>📅 Date:</strong> {issue.issuedate}</p>
                      <p><strong>⏰ Time:</strong> {issue.issuetime}</p>
                      <p><strong>📌 Type:</strong> {issue.issueType}</p>
                      <p>
                        <strong>✅ Status:</strong>{' '}
                        <span
                          style={{
                            padding: '5px 10px',
                            borderRadius: '10px',
                           backgroundColor:
                              issue.issuestatus === 'COMPLETED' ? '#d4edda'
                              : issue.issuestatus === 'NOT_STARTED' ? '#f8d7da'
                              : '#fff3cd',
                            color:
                              issue.issuestatus === 'COMPLETED' ? '#155724'
                              : issue.issuestatus === 'NOT_STARTED' ? '#721c24'
                              : '#856404',
                            fontWeight: '500'
                          }}
                        >
                          {issue.issuestatus}
                        </span>
                      </p>
                    </div>
                    <button
                      onClick={() => handleUpdate(issue.id)}
                      className="btn mt-3"
                      style={{
                        backgroundColor: '#F57C00',
                        color: '#fff',
                        fontWeight: 'bold',
                        borderRadius: '0.5rem'
                      }}
                    >
                      Update
                    </button>
                  </div>
                </div>
              </div>
            ))}
          </div>
        )}
      </div>
    </div>
  );
};

export default Employee;
