import React, { useEffect, useState } from 'react';
import axios from 'axios';

const AdminDashboard = () => {
  const [issues, setIssues] = useState([]);
  const [images, setImages] = useState([]);
  const [employees, setEmployees] = useState([]);
  const [selectedEmployee, setSelectedEmployee] = useState({});
  const [error, setError] = useState('');
  const [loadingAssign, setLoadingAssign] = useState(null);

  const token = localStorage.getItem("token");

  useEffect(() => {
    fetchIssues();
    fetchImages();
    fetchEmployees();
  }, [token]);

  const fetchIssues = async () => {
    try {
      const res = await axios.get("http://localhost:5731/Issue/getIssue/status", {
        headers: { Authorization: `Bearer ${token}` },
      });
      setIssues(res.data);
    } catch (err) {
      setError("Failed to fetch issues");
      console.error(err);
    }
  };

  const fetchImages = async () => {
    try {
      const res = await axios.get("http://localhost:5731/images/getall", {
        headers: { Authorization: `Bearer ${token}` },
      });
      setImages(res.data);
    } catch (err) {
      setError("Failed to fetch images");
      console.error(err);
    }
  };

  const fetchEmployees = async () => {
    try {
      const res = await axios.get("http://localhost:5731/users/get/MAINTANENCE", {
        headers: { Authorization: `Bearer ${token}` },
      });
      setEmployees(res.data);
    } catch (err) {
      setError("Failed to fetch employees");
      console.error(err);
    }
  };

  const mergedIssues = issues.map((issue) => {
    const imageGroup = images.find((img) => img.issue_id === issue.id);
    return {
      ...issue,
      issueimage: imageGroup ? imageGroup.images : [],
    };
  });

  const handleSelectChange = (issueId, employeeId) => {
    setSelectedEmployee(prev => ({ ...prev, [issueId]: employeeId }));
  };

  const assignWork = async (issueId) => {
    const employeeId = selectedEmployee[issueId];
    if (!employeeId) {
      alert("Please select an employee.");
      return;
    }

    try {
      setLoadingAssign(issueId);
      console.log("hello")
      await axios.put(`http://localhost:5731/Issue/assign/${issueId}/${employeeId}`, {}, {
        headers: { Authorization: `Bearer ${token}` },
      });
      alert("Employee assigned successfully.");
      fetchIssues();
    } catch (err) {
      alert("Assignment failed.");
      console.error(err);
    } finally {
      setLoadingAssign(null);
    }
  };

  return (
    <div style={{ backgroundColor: '#F9F9F9', minHeight: '100vh', padding: '2rem' }}>
      <div className="container">
        <h2 className="mb-4 text-center" style={{ color: '#1565C0', fontWeight: 'bold' }}>
          🛠 FixMy<span style={{ color: '#F57C00' }}>Theru</span> - All Reported Issues
        </h2>

        {error && <p className="text-danger text-center">{error}</p>}

        {mergedIssues.length === 0 ? (
          <p style={{ color: '#777', textAlign: 'center' }}>No issues reported.</p>
        ) : (
          <div className="row">
            {mergedIssues.map((issue) => (
              <div key={issue.id} className="col-md-4 mb-4">
                <div className="card h-100 shadow-sm" style={{ borderRadius: '1rem', border: 'none' }}>
                  {issue.issueimage.length > 0 && (
                    <img
                      src={`data:image/jpeg;base64,${issue.issueimage[0]}`}
                      alt={`Issue ${issue.id}`}
                      className="card-img-top"
                      style={{
                        height: '200px',
                        objectFit: 'cover',
                        borderTopLeftRadius: '1rem',
                        borderTopRightRadius: '1rem',
                      }}
                    />
                  )}
                  <div className="card-body d-flex flex-column justify-content-between">
                    <div>
                      <h5 className="card-title" style={{ color: '#F57C00', fontWeight: 'bold' }}>{issue.issuename}</h5>
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
                              issue.issuestatus === 'COMPLETED'
                                ? '#d4edda'
                                : issue.issuestatus === 'NOT_STARTED'
                                ? '#f8d7da'
                                : '#fff3cd',
                            color:
                              issue.issuestatus === 'COMPLETED'
                                ? '#155724'
                                : issue.issuestatus === 'NOT_STARTED'
                                ? '#721c24'
                                : '#856404',
                            fontWeight: '500',
                          }}
                        >
                          {issue.issuestatus}
                        </span>
                      </p>

                      {issue.issuestatus === "NOT_STARTED" && (
                        <>
                          <select
                            className="form-select mb-2"
                            value={selectedEmployee[issue.id] || ""}
                            onChange={(e) => handleSelectChange(issue.id, e.target.value)}
                          >
                            <option value="">Select Maintenance Employee</option>
                            {employees.map(emp => (
                              <option key={emp.id} value={emp.id}>
                                {emp.name} ({emp.email})
                              </option>
                            ))}
                          </select>

                          <button
                            className="btn btn-primary"
                            onClick={() => assignWork(issue.id)}
                            disabled={loadingAssign === issue.id}
                          >
                            {loadingAssign === issue.id ? "Assigning..." : "Assign Work"}
                          </button>
                        </>
                      )}
                    </div>

                    <div className="text-end mt-3">
                      <small className="text-muted">
                        Reported via <span style={{ color: '#1565C0', fontWeight: 'bold' }}>FixMy</span>
                        <span style={{ color: '#F57C00', fontWeight: 'bold' }}>Theru</span>
                      </small>
                    </div>
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

export default AdminDashboard;
