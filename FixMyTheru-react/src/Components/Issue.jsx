import React from 'react';
import { useLocation } from 'react-router-dom';
import { Carousel } from 'react-bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css';

const Issue = () => {
  const location = useLocation();
  const { issue } = location.state || {};

  if (!issue) return <div>No issue found</div>;

  const images = issue.issueimage || [];

  // Helper to format 24-hour time string (e.g., "14:30:00") to 12-hour AM/PM format
  const formatTime12Hour = (timeStr) => {
    if (!timeStr) return 'N/A';
    const parts = timeStr.split(':');
    if (parts.length < 2) return timeStr;
    let hours = parseInt(parts[0], 10);
    const minutes = parts[1];
    const ampm = hours >= 12 ? 'PM' : 'AM';
    hours = hours % 12;
    hours = hours ? hours : 12; // the hour '0' should be '12'
    return `${hours}:${minutes} ${ampm}`;
  };

  // Helper to map status to user-friendly label (e.g., "NOT_STARTED" -> "Submitted")
  const getDisplayStatus = (status) => {
    if (!status || status === 'NOT_STARTED' || status.toUpperCase() === 'NOT_STARTED') {
      return 'Submitted';
    }
    // Convert status with underscores to title case for aesthetics
    return status.replace(/_/g, ' ');
  };

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
          <h2 style={{ color: '#1565C0' }}>{issue.issuename}</h2>
          <p style={{ color: '#333' }}>{issue.issuedescription}</p>
          <p><strong>Date:</strong> {issue.issuedate || 'N/A'}</p>
          <p><strong>Time:</strong> {formatTime12Hour(issue.issuetime)}</p>
          <p><strong>Status:</strong> <span style={{ color: '#F57C00' }}>{getDisplayStatus(issue.issuestatus)}</span></p>
          <p><strong>Type:</strong> {issue.issueType || 'N/A'}</p>
          <p><strong>Location:</strong> {issue.issueLocation || 'N/A'}</p>
        </div>
      </div>
    </div>
  );
};

export default Issue;
