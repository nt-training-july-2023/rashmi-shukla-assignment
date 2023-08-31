import React, { useState } from "react";
import "./Navbar.css";
import { Link, useNavigate } from "react-router-dom";

const Navbar = () => {
  const [menuOpen, setMenuOpen] = useState(false);
  const navigate = useNavigate();

  const toggleMenu = () => {
    setMenuOpen(!menuOpen);
  };

  const handleLogout = () =>{
    localStorage.removeItem("IsLoggedIn")
    localStorage.removeItem("role")
    // navigate('/')
  }

  return (
    <div>
      <nav className="navbar">
        <button onClick={toggleMenu} className="hamburger-btn">
          &#9776;
        </button>
        <div className={`navbar-container ${menuOpen ? 'show':''}`}>
          <Link to="/dashboard" className="navbar-item">
            Home
          </Link>
          <Link to="/ListCategory" className="navbar-item">
            Category
          </Link>
          <Link to="#" className="navbar-item">
            Quizzes
          </Link>
          <Link to="#" className="navbar-item">
            Result
          </Link>
          <Link to='/' className="navbar-item"><button onClick={handleLogout} className="nav-button"> Logout
          </button></Link>
        </div>
      </nav>
    </div>
  );
};

export default Navbar;
