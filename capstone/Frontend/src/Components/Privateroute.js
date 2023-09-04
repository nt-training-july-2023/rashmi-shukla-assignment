import React from 'react'
import { Navigate, Outlet } from 'react-router-dom'

const Privateroute = () => {

    let loggedIn = true;

    return loggedIn ? <Outlet/> : <Navigate to="/dashboard" />;

}

export default Privateroute;
