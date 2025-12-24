import { useState, useEffect } from "react";
import "./AddUser.css";

export default function EditUser({ user, setPage, refreshList }) {
  const [form, setForm] = useState({
    name: "",
    email: "",
    mobile: "",
    age: "",
    role: "USER",
  });
  const [error, setError] = useState("");

  useEffect(() => {
    if (user) {
      setForm({
        name: user.name || "",
        email: user.email || "",
        mobile: user.mobile || "",
        age: user.age || "",
        role: user.role || "USER",
      });
    }
  }, [user]);

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError("");

    try {
      const response = await fetch(
        `http://localhost:5050/api/auth/user/update/${user._id}`,
        {
          method: "PUT",
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify(form),
        }
      );

      if (!response.ok) {
        const text = await response.text();
        setError(text);
        return;
      }

      alert("User updated successfully");
      if (refreshList) refreshList(); // refresh users/admins list
      setPage("viewUsers"); // or "viewAdmins" depending on parent
    } catch (err) {
      console.error(err);
      setError("Something went wrong");
    }
  };

  return (
    <div className="add-user-modal">
      <div className="add-user-card">
        <h2>Edit {form.role}</h2>
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
            <button type="submit">Save</button>
            <button type="button" onClick={() => setPage("viewUsers")}>
              Cancel
            </button>
          </div>
        </form>
      </div>
    </div>
  );
}
