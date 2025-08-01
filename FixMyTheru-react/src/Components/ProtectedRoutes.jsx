import React from 'react';
import { Navigate } from 'react-router-dom';

const ProtectedRoutes = ({ children, allowedRoles }) => {
  const token = localStorage.getItem("token");
  const role = localStorage.getItem("role");

  if (!token) {
    return <Navigate to="/" replace />;
  }

  if (allowedRoles && !allowedRoles.includes(role)) {
    return <h2 className="text-center mt-5">⛔ Access Restricted - You don't have permission.</h2>;
  }

  return children;
};

export default ProtectedRoutes;
