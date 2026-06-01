import React from 'react';
import { NavLink, Outlet, useNavigate } from 'react-router-dom';
const links = [['/admin','Tableau de bord'],['/admin/commandes','Commandes'],['/admin/menu','Menu']];
export default function AdminLayout() {
  const nav = useNavigate();
  return (
    <div className="app">
      <aside className="side">
        <div className="brand">Le Gourmet<span>·</span></div>
        <div className="sub">Espace staff</div>
        <div className="lab">GESTION</div>
        <nav>{links.map(([t,l])=><NavLink key={t} to={t} end className={({isActive})=>isActive?'active':''}>{l}</NavLink>)}</nav>
        <div className="side-foot">
          <button onClick={()=>{localStorage.removeItem('lg_admin');nav('/login');}}>Déconnexion</button>
        </div>
      </aside>
      <div className="main">
        <header className="topbar"><div className="tt"><h1>Administration</h1><div className="sub">Pilotage du restaurant</div></div>
          <div className="avatar">LG</div></header>
        <div className="content"><Outlet /></div>
      </div>
    </div>
  );
}
