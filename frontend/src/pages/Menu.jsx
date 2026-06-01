import React, { useEffect, useState } from 'react';
import { api } from '../api.js';

const CATS = [['TOUS','Tout'],['PIZZA','Pizzas'],['PATES','Pâtes'],['ENTREE','Entrées'],['DESSERT','Desserts'],['BOISSON','Boissons']];
const ICONS = { PIZZA:'🍕', PATES:'🍝', ENTREE:'🥗', DESSERT:'🍰', BOISSON:'🥤' };

export default function Menu() {
  const [plats, setPlats] = useState([]);
  const [filtre, setFiltre] = useState('TOUS');
  const [panier, setPanier] = useState([]);
  const [table, setTable] = useState('');
  const [ok, setOk] = useState(null);

  useEffect(() => { api.plats().then(setPlats).catch(()=>{}); }, []);
  const liste = filtre==='TOUS' ? plats : plats.filter(p=>p.categorie===filtre);
  const add = (p) => setPanier(prev => {
    const ex = prev.find(i=>i.id===p.id);
    return ex ? prev.map(i=>i.id===p.id?{...i,q:i.q+1}:i) : [...prev,{id:p.id,nom:p.nom,prix:p.prix,q:1}];
  });
  const total = panier.reduce((s,i)=>s+i.prix*i.q,0);
  async function commander() {
    const cmd = await api.addCommande({ tableNumero: table||'À emporter', lignes: panier.map(i=>({platId:i.id,quantite:i.q})) });
    setOk(cmd); setPanier([]);
  }

  return (
    <div className="menu-wrap">
      <div>
        <section className="hero">
          <h1>Le Gourmet <em>Pizzeria</em></h1>
          <p>Pizzas au feu de bois, pâtes fraîches et douceurs italiennes.</p>
        </section>
        <div className="chips">
          {CATS.map(([k,l])=><button key={k} className={filtre===k?'chip on':'chip'} onClick={()=>setFiltre(k)}>{l}</button>)}
        </div>
        <div className="grid">
          {liste.map(p=>(
            <article className="card" key={p.id}>
              <div className="card-img">{ICONS[p.categorie]||'🍴'}</div>
              <div className="card-body">
                <h3>{p.nom}</h3>
                <p className="card-desc">{p.description}</p>
                <div className="card-foot">
                  <span className="price">{p.prix.toFixed(0)} MAD</span>
                  <button className="btn-primary" onClick={()=>add(p)}>Ajouter</button>
                </div>
              </div>
            </article>
          ))}
        </div>
      </div>
      <aside className="panier">
        <h3>Commande</h3>
        {panier.length===0 ? <p className="muted">Sélectionnez des plats.</p> : (
          <>
            {panier.map(i=>(<div className="pline" key={i.id}><span>{i.q}× {i.nom}</span><b>{i.prix*i.q} MAD</b></div>))}
            <div className="ptotal">Total <b>{total} MAD</b></div>
            <input placeholder="N° de table (ex: T2)" value={table} onChange={e=>setTable(e.target.value)} />
            <button className="btn-primary full" onClick={commander}>Passer la commande</button>
          </>
        )}
        {ok && <div className="ok-msg">✓ Commande {ok.reference} envoyée en cuisine !</div>}
      </aside>
    </div>
  );
}
