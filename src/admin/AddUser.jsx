import { useState } from "react";
import "./AddUser.css";

export default function AddUser({ setShowAddUser, refreshUsers }) {
  const [form, setForm] = useState({
    name: "",
    email: "",
    mobile: "",
    age: "",
    role: "USER",
  });
  const [error, setError] = useState("");

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError("");

    try {
      const response = await fetch("http://localhost:5050/api/auth/add-user", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(form),
      });

      if (!response.ok) {
        const text = await response.text();
        setError(text);
        return;
      }

      alert("User added successfully");
      refreshUsers();
      setShowAddUser(false);
    } catch (err) {
      console.error(err);
      setError("Something went wrong");
    }
  };

  return (
    <div className="add-user-modal">
      <div className="add-user-card">
        <h2>Add New User</h2>
        <form onSubmit={handleSubmit}>
          <input
            name="name"
            placeholder="Name"
            value={form.name}
            onChange={handleChange}
            required
          />
          <input
            name="email"
            placeholder="Email"
            value={form.email}
            onChange={handleChange}
            required
          />
          <input
            name="mobile"
            placeholder="Mobile"
            value={form.mobile}
            onChange={handleChange}
          />
          <input
            name="age"
            placeholder="Age"
            value={form.age}
            onChange={handleChange}
          />
          <select name="role" value={form.role} onChange={handleChange}>
            <option value="USER">User</option>
            <option value="ADMIN">Admin</option>
          </select>

          {error && <p style={{ color: "red" }}>{error}</p>}

          <div className="add-user-buttons">
            <button type="submit">Add User</button>
            <button type="button" onClick={() => setShowAddUser(false)}>
              Cancel
            </button>
          </div>
        </form>
      </div>
    </div>
  );
}
