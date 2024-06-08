import { useState } from 'react';
import ClientLandingPage from './components/client/ClientLandingPage';
import Header from './components/commons/Header';
import Footer from './components/commons/Footer';
import './App.css';


function App() {

  return (
    <>
      <div className='w-full h-full flex flex-col'>
        <Header />
        <ClientLandingPage  />
        <Footer />
      </div>
    </>
  )
}

export default App;
