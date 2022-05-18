import React from 'react';
import './app.css';
import Footer from './components/Footer';
import GreetingText from './components/GreetingText';
import Header from './components/Header';
import DataCat from './components/ShowEventCategories';
import SearchBar from './components/SearchBar';
import NrEvents from './components/NrEvents';

/**
 * Base of application.
 * @returns the application with all its components
 */

export default function App() {
  return (
    <>
      <Header />
      <div className='background'>
        <div className='b-body'>
          <GreetingText />
          <SearchBar />
        </div>
      </div>
      <DataCat />
      <Footer />
    </>
  );
}