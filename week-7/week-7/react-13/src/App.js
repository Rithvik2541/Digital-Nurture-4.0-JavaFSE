import React, { useState } from 'react';
import BookDetails from './BookDetails';
import BlogDetails from './BlogDetails';
import CourseDetails from './CourseDetails';

function App() {
  const [view, setView] = useState('books');
  const books = ['React Handbook', 'JS Essentials', 'CSS Mastery'];
  const blogs = ['Hooks in Depth', 'React vs Angular', 'Next.js Guide'];
  const courses = ['React Basics', 'Advanced JS', 'Fullstack Bootcamp'];

  let content;
  if (view === 'books') content = <BookDetails books={books} />;
  else if (view === 'blogs') content = <BlogDetails blogs={blogs} />;
  else content = <CourseDetails courses={courses} />;

  return (
    <div style={{ padding: '20px' }}>
      <h1>Blogger App</h1>
      <button onClick={() => setView('books')}>Show Books</button>
      <button onClick={() => setView('blogs')}>Show Blogs</button>
      <button onClick={() => setView('courses')}>Show Courses</button>
      <hr />
      {view === 'books' && <BookDetails books={books} />}
      {view === 'blogs' ? <BlogDetails blogs={blogs} /> : null}
      {view === 'courses' && <CourseDetails courses={courses} />}
      <hr />
      {content}
    </div>
  );
}

export default App;
