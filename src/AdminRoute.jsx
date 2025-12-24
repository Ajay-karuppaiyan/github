import { Navigate } from "react-router-dom";

export default function AdminRoute({ children }) {
  const isLoggedIn = localStorage.getItem("isLoggedIn");
  const role = localStorage.getItem("role")?.toUpperCase();

  if (!isLoggedIn) {
    return <Navigate to="/signin" />;
  }

  if (role !== "ADMIN" && role !== "ROLE_ADMIN") {
    return <Navigate to="/dashboard" />;
  }

  return children;
}