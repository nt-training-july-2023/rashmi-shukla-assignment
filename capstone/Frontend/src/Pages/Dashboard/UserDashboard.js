import React, { useEffect } from 'react'
import Navbar from '../../Components/Navbar/Navbar'
import ErrorPage from '../ErrorPage/ErrorPage'

const UserDashboard = () => {

  useEffect(() => {
    window.history.pushState(null, '', '/user-dashboard');
    window.addEventListener('popstate', () => {
      window.history.pushState(null, '', '/user-dashboard');
    });
  },[])

  const userRole = localStorage.getItem('role');
  if (userRole !== 'user') {
    return (
        <ErrorPage/>
      );
    }
  
  return (
    <>
    <div>
        <Navbar/>
      <h1>Welcome to user dashboard!</h1>
    </div>
  </>
  )
}

export default UserDashboard
