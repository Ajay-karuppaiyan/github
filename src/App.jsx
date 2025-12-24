import { BrowserRouter, Routes, Route, Navigate } from "react-router-dom";
import Signup from "./Signup";
import Signin from "./Signin";
import Dashboard from "./Dashboard";
import AdminPanel from "./admin/AdminPanel";
import AddUser from "./admin/AddUser";
import AddAdmin from "./admin/AddAdmin";
import ProtectedRoute from "./ProtectedRoute";
import AdminRoute from "./AdminRoute.jsx";

function App() {
  return (
    <BrowserRouter>
      <Routes>

        {/* Default */}
        <Route path="/" element={<Navigate to="/signin" />} />

        {/* Auth */}
        <Route path="/signin" element={<Signin />} />
        <Route path="/signup" element={<Signup />} />

        {/* User Dashboard */}
        <Route
          path="/dashboard"
          element={
            <ProtectedRoute>
              <Dashboard />
            </ProtectedRoute>
          }
        />

        {/* Admin Routes */}
        <Route
          path="/admin"
          element={
            <AdminRoute>
              <AdminPanel />
            </AdminRoute>
          }
        />

        <Route
          path="/admin/add-user"
          element={
            <AdminRoute>
              <AddUser />
            </AdminRoute>
          }
        />

        <Route
          path="/admin/add-admin"
          element={
            <AdminRoute>
              <AddAdmin />
            </AdminRoute>
          }
        />

        {/* Fallback */}
        <Route path="*" element={<Navigate to="/signin" />} />

      </Routes>
    </BrowserRouter>
  );
}

export default App;
