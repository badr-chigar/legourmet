import React, { useEffect, useState } from 'react';
import { api } from '../api.js';
export default function AdminDashboard() {
  const [s, setS] = useState(null);
  useEffect(()=>{ api.stats().then(setS).catch(()=>{}); }, []);
  if (!s) return <div className="muted">…</div>;
  const cards = [
    ['Commandes', s.commandes, 'accent'],
    ['Réservations à venir', s.reservations, 'purple'],
    ["Chiffre d'affaires", Math.round(s.chiffreAffaires)+' MAD', 'ok'],
    ['Ingrédients bas', s.ingredientsBas, s.ingredientsBas>0?'warn':'ok'],
  ];
  return (
    <div>
      <div className="kpis">
        {cards.map(([l,v,c])=><div key={l} className={'kpi '+c}><div className="kpi-v">{v}</div><div className="kpi-l">{l}</div></div>)}
      </div>
      <h2>Plats au menu : {s.plats}</h2>
      <p className="muted">Gérez les commandes et le menu depuis le menu de gauche.</p>
    </div>
  );
}
