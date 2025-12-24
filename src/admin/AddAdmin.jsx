// import { useState } from "react";
// import "./AddAdmin.css";

// export default function AddAdmin({ setShowAddAdmin, refreshAdmins }) {
//   const [formData, setFormData] = useState({
//     name: "",
//     email: "",
//     password: "",
//     mobile: "",
//     age: "",
//   });

//   const handleChange = (e) => {
//     setFormData({
//       ...formData,
//       [e.target.name]: e.target.value,
//     });
//   };
 
//   const handleSubmit = async (e) => {
//     e.preventDefault();

//     try {
//       const res = await fetch("http://localhost:5050/api/auth/signup", {
//         method: "POST",
//         headers: {
//           "Content-Type": "application/json",
//         },
//         body: JSON.stringify({
//           ...formData,
//           role: "ADMIN", // ✅ THIS IS THE KEY LINE
//         }),
//       });

//       if (!res.ok) {
//         throw new Error("Failed to add admin");
//       }

//       alert("Admin added successfully");
//       refreshAdmins();      // refresh admin list
//       setShowAddAdmin(false); // close popup
//     } catch (err) {
//       alert("Error adding admin");
//     }
//   };

//   return (
//     <div className="add-user-modal">
//       <div className="add-user-box">
//         <h3>Add Admin</h3>

//         <form onSubmit={handleSubmit}>
//           <input
//             type="text"
//             name="name"
//             placeholder="Name"
//             value={formData.name}
//             onChange={handleChange}
//             required
//           />

//           <input
//             type="email"
//             name="email"
//             placeholder="Email"
//             value={formData.email}
//             onChange={handleChange}
//             required
//           />

//           <input
//             type="password"
//             name="password"
//             placeholder="Password"
//             value={formData.password}
//             onChange={handleChange}
//             required
//           />

//           <input
//             type="text"
//             name="mobile"
//             placeholder="Mobile"
//             value={formData.mobile}
//             onChange={handleChange}
//             required
//           />

//           <input
//             type="number"
//             name="age"
//             placeholder="Age"
//             value={formData.age}
//             onChange={handleChange}
//             required
//           />

//           <div className="btn-group">
//             <button type="submit">Add Admin</button>
//             <button
//               type="button"
//               onClick={() => setShowAddAdmin(false)}
//             >
//               Cancel
//             </button>
//           </div>
//         </form>
//       </div>
//     </div>
//   );
// }

import { useState, useEffect, useRef } from "react";
import { FaUser, FaUserShield, FaClipboardList, FaBars } from "react-icons/fa";
import ViewUsers from "./ViewUsers";
import EditUser from "./EditUser";
import Attendance from "./Attendance";
import ViewAdmins from "./ViewAdmins";
import AddAdmin from "./AddAdmin.jsx"; // ✅ explicit .jsx
import "./AdminPanel.css";

export default function AdminPanel() {
  const [page, setPage] = useState("dashboard");
  const [sidebarOpen, setSidebarOpen] = useState(false);
  const [openMenu, setOpenMenu] = useState(null);
  const [selectedUser, setSelectedUser] = useState(null);

  const [showAddAdmin, setShowAddAdmin] = useState(false); // ✅ modal state
  const sidebarRef = useRef(null);

  const toggleMenu = (menu) => setOpenMenu(openMenu === menu ? null : menu);
  const toggleSidebar = () => setSidebarOpen(!sidebarOpen);

  const navigatePage = (pageName) => {
    setPage(pageName);
    setSidebarOpen(false);
  };

  const handleLogout = () => {
    localStorage.clear();
    window.location.href = "/signin";
  };

  useEffect(() => {
    const handleClickOutside = (e) => {
      if (sidebarRef.current && !sidebarRef.current.contains(e.target)) {
        setSidebarOpen(false);
      }
    };
    document.addEventListener("mousedown", handleClickOutside);
    return () => document.removeEventListener("mousedown", handleClickOutside);
  }, []);

  // Refresh admins function for modal
  const refreshAdmins = () => {
    if (page === "viewAdmins") setPage("viewAdmins"); // triggers re-render
  };

  return (
    <div className="admin-panel">
      {/* ================= Sidebar ================= */}
      <aside ref={sidebarRef} className={`sidebar ${sidebarOpen ? "open" : ""}`}>
        <h2 className="admin-title">Admin Panel</h2>
        <ul className="menu">
          <li onClick={() => toggleMenu("users")}>
            <FaUser /> Users
          </li>
          {openMenu === "users" && (
            <ul className="sub-menu">
              <li onClick={() => navigatePage("viewUsers")}>View Users</li>
              <li onClick={() => navigatePage("attendance")}>Attendance</li>
            </ul>
          )}

          <li onClick={() => toggleMenu("admins")}>
            <FaUserShield /> Admins
          </li>
          {openMenu === "admins" && (
            <ul className="sub-menu">
              <li onClick={() => navigatePage("viewAdmins")}>View Admins</li>
              <li onClick={() => setShowAddAdmin(true)}>Add Admin</li> {/* ✅ open modal */}
            </ul>
          )}
        </ul>
      </aside>

      {/* ================= Topbar ================= */}
      <div className="topbar">
        <div className="top-left">
          <FaBars className="menu-icon" onClick={toggleSidebar} />
          <h3>Welcome, Admin</h3>
        </div>
        <button className="logout-btn" onClick={handleLogout}>Logout</button>
      </div>

      {sidebarOpen && <div className="overlay" onClick={toggleSidebar} />}

      {/* ================= Main Content ================= */}
      <main className="content">
        {page === "dashboard" && (
          <div className="page-box">
            <h2>Admin Dashboard</h2>
            <p>Select an option from the sidebar</p>
          </div>
        )}
        {page === "viewUsers" && (
          <ViewUsers setPage={setPage} setSelectedUser={setSelectedUser} />
        )}
        {page === "editUser" && <EditUser user={selectedUser} setPage={setPage} />}
        {page === "attendance" && <Attendance />}
        {page === "viewAdmins" && <ViewAdmins setPage={setPage} />}
      </main>

      {/* ================= Add Admin Modal ================= */}
      {showAddAdmin && (
        <AddAdmin setShowAddAdmin={setShowAddAdmin} refreshAdmins={refreshAdmins} />
      )}
    </div>
  );
}
