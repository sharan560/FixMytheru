import React from 'react';

const Navbar = () => {
  return (
    <nav style={styles.navbar}>
      <h1 style={styles.logo}>
        <span style={styles.fixMy}>FixMy</span>
        <span style={styles.theru}>Theru</span>
      </h1>
      <img src='/src/assets/ChatGPT Image Jul 17, 2025, 09_57_15 AM.png'/>
      <div style={styles.tamil}>
        <span role="img" aria-label="wrench" style={styles.wrench}>🔧</span> 
        <span>தெரு</span>
      </div>
    </nav>
  );
};

const styles = {
  navbar: {
    display: 'flex',
    justifyContent: 'space-between',
    alignItems: 'center',
    padding: '1rem 2rem',
    backgroundColor: '#1565C0', // Deep Blue
    color: '#FFFFFF',
  },
  logo: {
    fontSize: '1.8rem',
    fontWeight: 'bold',
  },
  fixMy: {
    color: '#1565C0', // Deep Blue
    backgroundColor: '#FFFFFF',
    padding: '0 5px',
    borderRadius: '5px',
    marginRight: '4px',
  },
  theru: {
    color: '#F57C00', // Orange
  },
  tamil: {
    fontSize: '1.2rem',
    color: '#FFFFFF',
    display: 'flex',
    alignItems: 'center',
    gap: '8px',
  },
  wrench: {
    fontSize: '1.5rem',
  }
};

export default Navbar;
