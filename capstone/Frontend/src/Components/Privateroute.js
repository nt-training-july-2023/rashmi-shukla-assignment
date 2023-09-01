import React from 'react'
import { Navigate, Outlet } from 'react-router-dom'
import AdminDashboard from './AdminDashboard';

const Privateroute = () => {

    let loggedIn = true;

    if(loggedIn){
        return <Outlet/>
    }else{
        return <Navigate to='/admin-dashboard'/>
    }

}

export default Privateroute
