import React from 'react';

const IndianPlayers = () => {
  const T20players = ['Rohit', 'SKY', 'Jadeja', 'Bumrah'];
  const RanjiTrophyPlayers = ['Pujara', 'Karun', 'Nair', 'Iyer'];
  const allPlayers = [...T20players, ...RanjiTrophyPlayers];
  const oddPlayers = allPlayers.filter((_, i) => i % 2 !== 0);
  const evenPlayers = allPlayers.filter((_, i) => i % 2 === 0);

  return (
    <div>
      <h2>All Indian Players (Merged)</h2>
      <ul>{allPlayers.map((p, i) => <li key={i}>{p}</li>)}</ul>
      <h3>Odd Team Players</h3>
      <ul>{oddPlayers.map((p, i) => <li key={i}>{p}</li>)}</ul>
      <h3>Even Team Players</h3>
      <ul>{evenPlayers.map((p, i) => <li key={i}>{p}</li>)}</ul>
    </div>
  );
};

export default IndianPlayers;
