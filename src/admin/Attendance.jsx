import { useEffect, useState } from "react";
import "./Attendance.css";

export default function Attendance() {
  const [users, setUsers] = useState([]);
  const [loading, setLoading] = useState(true);

  // Filter states
  const [filterType, setFilterType] = useState("date"); // 'date' or 'period'
  const [singleDate, setSingleDate] = useState("");
  const [startDate, setStartDate] = useState("");
  const [endDate, setEndDate] = useState("");

  // 🔹 Fetch attendance users
  const fetchAttendance = async () => {
    try {
      const res = await fetch("http://localhost:5050/api/auth/attendance");
      const data = await res.json();
      setUsers(data || []);
    } catch (err) {
      console.error("Failed to fetch attendance", err);
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchAttendance();
  }, []);

  // 🔹 Mark attendance
  const markAttendance = async (userId) => {
    try {
      const res = await fetch(
        `http://localhost:5050/api/auth/attendance/${userId}`,
        { method: "POST" }
      );
      const data = await res.json();
      alert(data.message);
      fetchAttendance(); // refresh
    } catch (err) {
      console.error("Failed to mark attendance", err);
    }
  };

  // 🔹 Filter users based on selected filter type
  const filteredUsers = users.map((user) => {
    let filteredAttendance = user.attendance || [];

    if (filterType === "date" && singleDate) {
      filteredAttendance = filteredAttendance.filter((d) => d === singleDate);
    } else if (
      filterType === "period" &&
      startDate &&
      endDate
    ) {
      filteredAttendance = filteredAttendance.filter(
        (d) => d >= startDate && d <= endDate
      );
    }

    return { ...user, attendance: filteredAttendance };
  });

  if (loading) return <p style={{ padding: "20px" }}>Loading attendance...</p>;

  return (
    <div className="attendance">
      <div className="attendance-header">
        <h2>User Attendance</h2>

        <div className="filter">
          <label>Filter Type:</label>
          <select
            value={filterType}
            onChange={(e) => setFilterType(e.target.value)}
          >
            <option value="date">By Date</option>
            <option value="period">By Period</option>
          </select>

          {filterType === "date" && (
            <input
              type="date"
              value={singleDate}
              onChange={(e) => setSingleDate(e.target.value)}
            />
          )}

          {filterType === "period" && (
            <>
              <input
                type="date"
                value={startDate}
                onChange={(e) => setStartDate(e.target.value)}
              />
              <span className="to-text">to</span>
              <input
                type="date"
                value={endDate}
                onChange={(e) => setEndDate(e.target.value)}
              />
            </>
          )}
        </div>
      </div>

      <table className="attendance-table">
        <thead>
          <tr>
            <th>Name</th>
            <th>Email</th>
            <th>Attendance</th>
            <th>Action</th>
          </tr>
        </thead>

        <tbody>
          {filteredUsers.length === 0 && (
            <tr>
              <td colSpan="4" style={{ textAlign: "center" }}>
                No users found
              </td>
            </tr>
          )}

          {filteredUsers.map((user) => (
            <tr key={user._id || user.id}>
              <td>{user.name}</td>
              <td>{user.email}</td>
              <td>
                {user.attendance && user.attendance.length > 0
                  ? user.attendance.join(", ")
                  : "No records"}
              </td>
              <td>
                <button
                  className="mark-btn"
                  onClick={() => markAttendance(user._id || user.id)}
                >
                  Mark Today
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}
