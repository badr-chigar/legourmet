import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
export default function Login() {
  const [email, setEmail] = useState('admin@legourmet.ma');
  const [mdp, setMdp] = useState('admin123');
  const [err, setErr] = useState('');
  const nav = useNavigate();
  function submit(e){
    e.preventDefault();
    if (email==='admin@legourmet.ma' && mdp==='admin123'){ localStorage.setItem('lg_admin','1'); nav('/admin'); }
    else setErr('Identifiants invalides');
  }
  return (
    <div className="login-wrap"><form className="login" onSubmit={submit}>
      <div className="brand big">Le Gourmet<span>·</span></div>
      <p className="muted">Espace staff</p>
      <label>Email<input type="email" value={email} onChange={e=>setEmail(e.target.value)} /></label>
      <label>Mot de passe<input type="password" value={mdp} onChange={e=>setMdp(e.target.value)} /></label>
      {err && <div className="error">{err}</div>}
      <button type="submit">Se connecter</button>
      <small className="muted">Démo : admin@legourmet.ma / admin123</small>
    </form></div>
  );
}
