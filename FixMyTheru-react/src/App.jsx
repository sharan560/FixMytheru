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

const App = () => {
  return (
    <BrowserRouter>
      <Navbar />
      <Routes>
        <Route path="/" element={<Login />} />
        <Route path="/register" element={<Register />} />
        <Route path="/home" element={<Home />} />
        <Route path="/Profile" element={<Profile />} />
        <Route path="/employee" element={<Employee/>}/>
        <Route path="/update" element={<WorkUpdateForm/>}/>
        <Route path="/admin" element={<AdminDashboard/>}/>
        <Route path="/empform" element={<EmployeeForm/>}/>
        <Route path="/raiseissue" element={<RaiseIssue/>} />
        <Route path="/issue/:id" element={<Issue/>} />
        <Route path="*" element={<h2 className="text-center mt-5">404 - Page Not Found</h2>} />
      </Routes>
    </BrowserRouter>
  );
};

export default App;
