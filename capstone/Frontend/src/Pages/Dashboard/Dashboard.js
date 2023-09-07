import React,{useEffect} from 'react'
import Navbar from '../../Components/Navbar/Navbar'  //.. represents parent dir
import './Dashboard.css'
import ErrorPage from '../ErrorPage/ErrorPage'

const Dashboard = () => {

  useEffect(() => {
    window.history.pushState(null, '', '/dashboard');
    window.addEventListener('popstate', () => {
      window.history.pushState(null, '', '/dashboard');
    });
  }, []);

  const userRole = localStorage.getItem('role');
  if (userRole !== 'admin') {
    return (
        <ErrorPage/>
      );
    }

  return (
    <>
    <div>
      <Navbar/>
      <h1>Welcome to the admin dashboard!</h1>
    </div>
    </>
  )
}

export default Dashboard
