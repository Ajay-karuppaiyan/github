import { useState, useEffect } from "react";
import AddAdmin from "./AddAdmin";

export default function ViewAdmins({ setPage }) {
  const [admins, setAdmins] = useState([]);
  const [showAddAdmin, setShowAddAdmin] = useState(false);

  const fetchAdmins = async () => {
    try {
      const res = await fetch("http://localhost:5050/api/auth/admins"); // your API endpoint
      const data = await res.json();
      setAdmins(data);
    } catch (err) {
      console.error(err);
    }
  };

  useEffect(() => {
    fetchAdmins();
  }, []);

  return (
    <div>
      <h2>Admins</h2>
      <button onClick={() => setShowAddAdmin(true)}>Add Admin</button>

      <ul>
        {admins.map((admin) => (
          <li key={admin._id}>{admin.name} - {admin.email}</li>
        ))}
      </ul>

      {showAddAdmin && (
        <AddAdmin
          setShowAddAdmin={setShowAddAdmin}
          refreshAdmins={fetchAdmins}
        />
      )}
    </div>
  );
}
