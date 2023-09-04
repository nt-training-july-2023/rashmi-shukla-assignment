import React, { useEffect, useState } from "react";
import "./Navbar.css";
import Swal from "sweetalert2";
import { Link, useNavigate } from "react-router-dom";

const Navbar = () => {
  const [valid, setValid] = useState("");
    const result = localStorage.getItem("role");
    const IsLoggedIn = localStorage.getItem("IsLoggedIn");

    useEffect(() => {
        if (result === "admin") {
      setValid("true");
        } else {
      setValid("false");
    }});

  const [menuOpen, setMenuOpen] = useState(false);
  const navigate = useNavigate();

  const toggleMenu = () => {
    setMenuOpen(!menuOpen);
  };

  const handleLogoutConfirmation =() =>{
    Swal.fire({
      text:"Confirm Logout?",
      icon:"warning",
      showCancelButton:true,
      showConfirmButton:true,
    }).then((result)=>{
      if(result.isConfirmed){
        handleLogout();
      }
      });
  }

  const handleLogout = () =>{
    navigate('/')
    Swal.fire({
      text: "You've successfully logged out!",
      icon: "success",
      timer: 2000,
      showConfirmButton:false
    });
    localStorage.removeItem("IsLoggedIn")
    localStorage.removeItem("role")
  }

  return (
    <div>
      <nav className="navbar">
        <button onClick={toggleMenu} className="hamburger-btn">
          &#9776;
        </button>
        <div className={`navbar-container ${menuOpen ? 'show':''}`}>
          {(valid==="true") ? (<Link to="/dashboard" className="navbar-item">
            Home
          </Link>): (<Link to="/user-dashboard" className="navbar-item">
            Home
          </Link>)}

          <Link to="/ListCategory" className="navbar-item">
            Category
          </Link>
          <Link to="#" className="navbar-item">
            Quizzes
          </Link> 
          <Link to="#" className="navbar-item">
            Result
          </Link>
          <Link to='#' className="navbar-item"><button onClick={handleLogoutConfirmation} className="nav-button"> Logout </button></Link>
        </div>
      </nav>
    </div>
  );
};

export default Navbar;
