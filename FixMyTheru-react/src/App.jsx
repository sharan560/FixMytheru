import React from 'react';
import { BrowserRouter, Routes, Route, Navigate } from 'react-router-dom';
import Navbar from './Components/Navbar';
import Login from './Components/Login';
import Register from './Components/Register';
import Home from './Components/Home';
import Profile from './Components/Profile';
import Employee from './Components/Employee';
import WorkUpdateForm from './Components/WorkUpdateform';
import AdminDashboard from './Components/AdminDashboard';
import EmployeeForm from './Components/EmployeeForm';
import RaiseIssue from './Components/RaiseIssue';
import Issue from './Components/Issue';
import ProtectedRoute from './Components/ProtectedRoutes'; 

const App = () => {
  return (
    <BrowserRouter>
      <Navbar />
      <Routes>
        <Route path="/" element={<Login />} />
        <Route path="/register" element={<Register />} />

        <Route
          path="/home"
          element={
            <ProtectedRoute allowedRoles={["USER"]}>
              <Home />
            </ProtectedRoute>
          }
        />

        <Route
          path="/profile"
          element={
            <ProtectedRoute allowedRoles={["ADMIN", "USER", "MAINTENANCE"]}>
              <Profile />
            </ProtectedRoute>
          }
        />

        <Route
          path="/employee"
          element={
            <ProtectedRoute allowedRoles={["MAINTENANCE"]}>
              <Employee />
            </ProtectedRoute>
          }
        />

        <Route
          path="/update"
          element={
            <ProtectedRoute allowedRoles={["MAINTENANCE"]}>
              <WorkUpdateForm />
            </ProtectedRoute>
          }
        />

        <Route
          path="/admin"
          element={
            <ProtectedRoute allowedRoles={["ADMIN"]}>
              <AdminDashboard />
            </ProtectedRoute>
          }
        />

        <Route
          path="/empform"
          element={
            <ProtectedRoute allowedRoles={["ADMIN"]}>
              <EmployeeForm />
            </ProtectedRoute>
          }
        />

        <Route
          path="/raiseissue"
          element={
            <ProtectedRoute allowedRoles={["USER"]}>
              <RaiseIssue />
            </ProtectedRoute>
          }
        />

        <Route
          path="/issue/:id"
          element={
            <ProtectedRoute allowedRoles={["ADMIN", "MAINTENANCE","USER"]}>
              <Issue />
            </ProtectedRoute>
          }
        />
        <Route path="*" element={<h2 className="text-center mt-5">404 - Page Not Found</h2>} />
      </Routes>
    </BrowserRouter>
  );
};

export default App;
