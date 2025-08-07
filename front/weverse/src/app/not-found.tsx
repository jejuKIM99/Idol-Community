import Link from 'next/link';

export default function NotFound() {
  return (
    <div style={{
      display: 'flex',
      flexDirection: 'column',
      alignItems: 'center',
      justifyContent: 'center',
      minHeight: '100vh',
      textAlign: 'center',
      backgroundColor: '#f8f8f8',
      color: '#333',
      fontFamily: 'Arial, sans-serif',
    }}>
      <h1 style={{ fontSize: '4rem', marginBottom: '20px', color: '#e74c3c' }}>404</h1>
      <h2 style={{ fontSize: '2rem', marginBottom: '10px' }}>Not Found</h2>
      <p style={{ fontSize: '1.2rem', marginBottom: '30px' }}>Could not find requested resource</p>
      <Link href="/" style={{
        padding: '10px 20px',
        backgroundColor: '#3498db',
        color: 'white',
        textDecoration: 'none',
        borderRadius: '5px',
        transition: 'background-color 0.3s ease',
      }}>
        Return Home
      </Link>
    </div>
  );
}