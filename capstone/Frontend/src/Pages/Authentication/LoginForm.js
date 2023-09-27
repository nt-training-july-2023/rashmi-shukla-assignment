import React, { useState } from "react";
import axios from "axios";
import Swal from "sweetalert2";
import { useNavigate } from "react-router-dom";
import "./Form.css";

const LoginForm = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [errors, setErrors] = useState({});
  const navigate = useNavigate();

  const handleLoginClick = async (event) => {
    event.preventDefault();

    const validattionErrors = {};
    if (email === "") {
      validattionErrors.email = "*required";
    }
    if (password === "") {
      validattionErrors.password = "*required";
    }

    if (Object.keys(validattionErrors).length === 0) {
      try {
        const response = await axios.post("http://localhost:8080/users/login", {
          email,
          password,
        });
        if (response.status === 200 && response.data.role === "user") {
          // Redirect upon successful login
          navigate("/user-dashboard");
        } else if (response.status === 200 && response.data.role === "admin") {
          navigate("/dashboard");
        }
        localStorage.setItem("IsLoggedIn", response.status);
        localStorage.setItem("role", response.data.role);
        localStorage.setItem("userName", response.data.fullName)
        localStorage.setItem("userEmail", response.data.email)
      } catch (error) {
        const submitError = error.response.data.message;
        Swal.fire({
          title: "Error",
          text: `${submitError}`,
          icon: "error",
          confirmButtonText: "Retry",
          confirmButtonColor: "red",
        });
        setEmail("");
        setPassword("");
      }
    } else {
      setErrors(validattionErrors);
    }
  };

  const redirectToRegister = () => {
    navigate("/register");
  };

  return (
    <div className="form-body">
      <div className="content-container">
        <h1>ASSESSMENT PORTAL</h1>
        <p>"Assess, Excel, Succeed"</p>
        <button onClick={redirectToRegister}>Get Started</button>
      </div>
      <div className="Auth-form-container">
        <form className="Auth-form" onSubmit={handleLoginClick}>
          <div className="Auth-form-content">
            <h3 className="Auth-form-title">Login</h3>
            <div className="form-group">
              <label>Email address</label>
              <input
                type="email"
                className="form-input"
                value={email}
                onChange={(e) => {
                  setEmail(e.target.value);
                  setErrors("");
                }}
              />
              {errors.email && <span>{errors.email}</span>}
            </div>

            <div className="form-group">
              <label>Password</label>
              <input
                type="password"
                className="form-input"
                value={password}
                onChange={(e) =>{
                  setPassword(e.target.value);
                  setErrors("");
                }}
              />
              {errors.password && <span>{errors.password}</span>}
            </div>

            <div className="button-group">
              <button type="submit" className="button">
                Login
              </button>
            </div>
          </div>
        </form>
      </div>
    </div>
  );
};

export default LoginForm;
