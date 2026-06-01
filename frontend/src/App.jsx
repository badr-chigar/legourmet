import React from 'react';
import { HashRouter, Routes, Route, Navigate } from 'react-router-dom';
import ClientLayout from './components/ClientLayout.jsx';
import AdminLayout from './components/AdminLayout.jsx';
import Menu from './pages/Menu.jsx';
import Reservation from './pages/Reservation.jsx';
import Login from './pages/Login.jsx';
import AdminDashboard from './pages/AdminDashboard.jsx';
import AdminCommandes from './pages/AdminCommandes.jsx';
import AdminMenu from './pages/AdminMenu.jsx';

const RequireAdmin = ({ children }) =>
  localStorage.getItem('lg_admin') === '1' ? children : <Navigate to="/login" replace />;

export default function App() {
  return (
    <HashRouter>
      <Routes>
        <Route element={<ClientLayout />}>
          <Route path="/" element={<Menu />} />
          <Route path="/reservation" element={<Reservation />} />
        </Route>
        <Route path="/login" element={<Login />} />
        <Route element={<RequireAdmin><AdminLayout /></RequireAdmin>}>
          <Route path="/admin" element={<AdminDashboard />} />
          <Route path="/admin/commandes" element={<AdminCommandes />} />
          <Route path="/admin/menu" element={<AdminMenu />} />
        </Route>
        <Route path="*" element={<Navigate to="/" replace />} />
      </Routes>
    </HashRouter>
  );
}
