import { useState } from "react";
import { useNavigate } from "react-router-dom";
import "./Signin.css";

export default function Signin() {
  const navigate = useNavigate();

  const [form, setForm] = useState({
    email: "",
    password: "",
  });

  const [error, setError] = useState("");
  const [loading, setLoading] = useState(false);

  function handleChange(e) {
    setForm({ ...form, [e.target.name]: e.target.value });
  }

async function handleSubmit(e) {
  e.preventDefault();
  setError("");
  setLoading(true);

  try {
    const response = await fetch("http://localhost:8000/api/auth/login", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(form),
    });

    if (!response.ok) {
      const text = await response.text();
      setError(text);
      setLoading(false);
      return;
    }

    // Backend returns raw JWT string
    const token = await response.text();

    // Save token to localStorage
    localStorage.setItem("token", token);
    localStorage.setItem("isLoggedIn", "true");

    alert("Login successful");

    // Navigate to Admin Panel
    navigate("/admin");
  } catch (err) {
    console.error(err);
    setError("Server error. Please try again.");
  }

  setLoading(false);
}

  return (
    <div className="signin-container">
      <div className="signin-card">
        <h2>Sign In</h2>

        <form onSubmit={handleSubmit}>
          <input
            type="email"
            name="email"
            placeholder="Email"
            value={form.email}
            onChange={handleChange}
            required
          />

          <input
            type="password"
            name="password"
            placeholder="Password"
            value={form.password}
            onChange={handleChange}
            required
          />

          <button type="submit" disabled={loading}>
            {loading ? "Signing in..." : "Sign In"}
          </button>

          {error && <p style={{ color: "red", textAlign: "center" }}>{error}</p>}

          <p style={{ textAlign: "center", marginTop: "10px" }}>
            Don't have an account?{" "}
            <span
              style={{ color: "blue", cursor: "pointer" }}
              onClick={() => navigate("/signup")}
            >
              Signup
            </span>
          </p>
        </form>
      </div>
    </div>
  );
}
