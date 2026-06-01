import React from 'react';
import { Link, NavLink, Outlet } from 'react-router-dom';
export default function ClientLayout() {
  return (
    <div className="shop">
      <header className="shop-head">
        <div className="logo">Le Gourmet<span>·</span></div>
        <nav className="shop-nav">
          <NavLink to="/" end>Menu</NavLink>
          <NavLink to="/reservation">Réserver</NavLink>
          <a href="#/login">Espace staff</a>
        </nav>
      </header>
      <main className="shop-main"><Outlet /></main>
      <footer className="shop-foot">© 2026 Le Gourmet — Pizzeria · Badr Chigar</footer>
    </div>
  );
}
