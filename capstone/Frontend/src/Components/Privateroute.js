import React from 'react'
import { Navigate, Outlet } from 'react-router-dom'

const Privateroute = () => {

    let loggedIn = true;

    if(loggedIn){
        return <Outlet/>
    }else{
        return <Navigate to='/'/>
    }

}

export default Privateroute
