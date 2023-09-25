import React, { useEffect } from 'react'
import Navbar from '../../Components/Navbar/Navbar'
import welcomeImg from  './welcome-img.png';
import ErrorPage from '../ErrorPage/ErrorPage'

const UserDashboard = () => {

  const userName = localStorage.getItem("userName");

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
    <div className='dashboard-container'>
      <Navbar/>
      <div className='dashboard-info'>
          <h1>USER DASHBOARD</h1>
      </div>
      <div className='dashboard-content'>
          <div className='welcome-box'>
              <h1>Welcome, {userName}</h1>
              <div>
              <img className='welcome-img' src={welcomeImg} alt="img"/>
              </div>
          </div>
          <div className='user-profile-box'>
            <h2>Check Your Progress:</h2>
            {/* <button></button> */}
          </div>
      </div>
      
    </div>
  )
}

export default UserDashboard
