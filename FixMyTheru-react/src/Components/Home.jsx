import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

const Home = () => {
  const [issues, setIssues] = useState([]);
  const [images, setImages] = useState([]);
  const [error, setError] = useState('');
  const navigate = useNavigate();

  const token = localStorage.getItem("token");

  useEffect(() => {
    axios.get("https://fixmytheru.onrender.com/Issue/getall", {
      headers: { Authorization: `Bearer ${token}` }
    })
    .then((response) => setIssues(response.data))
    .catch((err) => {
      setError('Failed to fetch issues');
      console.error(err);
    });
  }, [token]);

  useEffect(() => {
    axios.get("https://fixmytheru.onrender.com/images/getall", {
      headers: { Authorization: `Bearer ${token}` }
    })
    .then((response) => setImages(response.data))
    .catch((err) => {
      setError('Failed to fetch images');
      console.error(err);
    });
  }, [token]);

  const mergedIssues = issues.map((issue) => {
    const imageGroup = images.find((img) => img.issue_id === issue.id);
    return {
      ...issue,
      issueimage: imageGroup ? imageGroup.images : [],
    };
  });

  const handleCardClick = (issue) => {
    navigate(`/issue/${issue.id}`, { state: { issue } });  
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
                <div
                  className="card h-100 shadow"
                  style={{ borderRadius: '1rem', border: 'none', cursor: 'pointer' }}
                  onClick={() => handleCardClick(issue)}
                >
                  {issue.issueimage.length > 0 && (
                    <img
                      src={`data:image/jpeg;base64,${issue.issueimage[0]}`}
                      alt={`Issue ${issue.id}`}
                      style={{
                        width: '100%',
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
                              issue.issuestatus === 'COMPLETED' ? '#d4edda'
                              : issue.issuestatus === 'NOT_STARTED' ? '#f8d7da'
                              : '#fff3cd',
                            color:
                              issue.issuestatus === 'COMPLETED' ? '#155724'
                              : issue.issuestatus === 'NOT_STARTED' ? '#721c24'
                              : '#856404',
                            fontWeight: '500',
                          }}
                        >
                          {issue.issuestatus}
                        </span>
                      </p>
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

export default Home;
