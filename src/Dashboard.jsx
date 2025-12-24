import "./dashboard.css";
import { useNavigate } from "react-router-dom";
import { useEffect, useRef } from "react";

export default function Dashboard() {
  const navigate = useNavigate();
  const timerRef = useRef(null);

  const name = localStorage.getItem("name");
  const role = localStorage.getItem("role");

  // Clear user data
  function clearUserData() {
    localStorage.removeItem("id");
    localStorage.removeItem("name");
    localStorage.removeItem("email");
    localStorage.removeItem("age");
    localStorage.removeItem("mobile");
    localStorage.removeItem("role");
    localStorage.removeItem("isLoggedIn");
  }

  // Logout function
  function logout() {
    clearUserData();
    navigate("/signin");
  }

  // Reset the inactivity timer
  function resetTimer() {
    if (timerRef.current) {
      clearTimeout(timerRef.current);
    }

    // New 30 second inactivity timer
    timerRef.current = setTimeout(() => {
      logout();
    }, 30000);
  }

  useEffect(() => {
    // Block unlogged or admin users
    if (localStorage.getItem("isLoggedIn") !== "true") {
      navigate("/signin");
      return;
    }

    // Activity events
    const events = [
      "mousemove",
      "keydown",
      "click",
      "scroll",
      "touchstart",
    ];

    // When any activity happens → reset timer
    events.forEach((event) =>
      window.addEventListener(event, resetTimer)
    );

    // Start timer on load
    resetTimer();

    return () => {
      // Cleanup listeners and timer on unmount
      events.forEach((event) =>
        window.removeEventListener(event, resetTimer)
      );
      clearTimeout(timerRef.current);
    };
  }, [navigate, role]);

  return (
    <div className="dashboard-container">
      <header className="dashboard-header">
        <h1>Welcome to the dashboard {name} 👋</h1>
      </header>

      <div className="dashboard-content">

        <div className="dashboard-card">
          <h3>Your Profile</h3>
          <p>View and update your personal information.</p>
        </div>

        <div className="dashboard-card">
          <h3>Settings</h3>
          <p>Manage your account and preferences.</p>
        </div>

        <div className="dashboard-card">
          <h3>Support</h3>
          <p>Contact support for any help you need.</p>
        </div>

      </div>

      <button className="dashboard-logout" onClick={logout}>
        Logout
      </button>
    </div>
  );
}
