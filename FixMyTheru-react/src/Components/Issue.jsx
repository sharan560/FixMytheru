import React from 'react';
import { useLocation } from 'react-router-dom';
import { Carousel } from 'react-bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css';

const Issue = () => {
  const location = useLocation();
  const { issue } = location.state || {};

  if (!issue) return <div>No issue found</div>;

  const images = issue.issueimage || [];

  return (
    <div className="container mt-5 p-4 rounded" style={{ backgroundColor: '#f5f5f5' }}>
      <div className="row">
        <div className="col-md-6">
          {images.length > 0 ? (
            <Carousel>
              {images.map((img, index) => (
                <Carousel.Item key={index}>
                  <img
                    className="d-block w-100 rounded"
                    src={`data:image/jpeg;base64,${img}`}
                    alt={`Slide ${index + 1}`}
                    style={{ height: '400px', objectFit: 'cover' }}
                  />
                </Carousel.Item>
              ))}
            </Carousel>
          ) : (
            <div
              className="d-flex align-items-center justify-content-center bg-secondary text-white"
              style={{ height: '400px', borderRadius: '10px' }}
            >
              No Image Available
            </div>
          )}
        </div>

        <div className="col-md-6">
          <h2 style={{ color: '#1565C0' }}>{issue.issueName}</h2>
          <p style={{ color: '#333' }}>{issue.issueDescription}</p>
          <p><strong>Date:</strong> {issue.issueDate}</p>
          <p><strong>Time:</strong> {issue.issueTime}</p>
          <p><strong>Status:</strong> <span style={{ color: '#F57C00' }}>{issue.issueStatus}</span></p>
          <p><strong>Type:</strong> {issue.issueType}</p>
          <p><strong>Location:</strong> {issue.issueLocation}</p>
        </div>
      </div>
    </div>
  );
};

export default Issue;
