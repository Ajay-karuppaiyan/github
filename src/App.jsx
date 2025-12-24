import { BrowserRouter, Routes, Route, Navigate } from "react-router-dom";

import Signup from "./Signup";
import Signin from "./Signin";
import Dashboard from "./Dashboard";

import AdminPanel from "./admin/AdminPanel";
import AddUser from "./admin/AddUser";
import AddAdmin from "./admin/AddAdmin";
import EditUser from "./admin/EditUser";
import EditAdmin from "./admin/EditAdmin";

import ProtectedRoute from "./ProtectedRoute";
import AdminRoute from "./AdminRoute";

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

        {/* Admin Panel */}
        <Route
          path="/admin"
          element={
            <AdminRoute>
              <AdminPanel />
            </AdminRoute>
          }
        />

        {/* Admin - Users */}
        <Route
          path="/admin/add-user"
          element={
            <AdminRoute>
              <AddUser />
            </AdminRoute>
          }
        />

        <Route
          path="/admin/edit-user/:id"
          element={
            <AdminRoute>
              <EditUser />
            </AdminRoute>
          }
        />

        {/* Admin - Admins */}
        <Route
          path="/admin/add-admin"
          element={
            <AdminRoute>
              <AddAdmin />
            </AdminRoute>
          }
        />

        <Route
          path="/admin/edit-admin/:id"
          element={
            <AdminRoute>
              <EditAdmin />
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
