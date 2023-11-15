document.addEventListener('DOMContentLoaded', function () {
  const mobileMenu = document.getElementById('mobile-menu');
  let localContextInUrl = '';

  function setLocalContext() {
    switch (COMPODOC_CURRENT_PAGE_CONTEXT) {
      case 'additional-page':
        localContextInUrl = 'additional-documentation';
        break;
      case 'class':
        localContextInUrl = 'classes';
        break;
      case 'miscellaneous-functions':
      case 'miscellaneous-variables':
      case 'miscellaneous-typealiases':
      case 'miscellaneous-enumerations':
        localContextInUrl = 'miscellaneous';
        break;
      default:
        break;
    }
  }

  function hasClass(el, cls) {
    return el.className && new RegExp('(\\s|^)' + cls + '(\\s|$)').test(el.className);
  }

  function processLink(link, url) {
    if (url.charAt(0) !== '.') {
      let prefix = Array(COMPODOC_CURRENT_PAGE_DEPTH + 2).join('../');
      link.setAttribute('href', prefix + url);
    }
  }

  function processMenuLinks(links, dontAddClass) {
    for (let i = 0; i < links.length; i++) {
      let link = links[i];
      let linkHref = link.getAttribute('href');
      if (linkHref) {
        let linkHrefFile = linkHref.substr(linkHref.lastIndexOf('/') + 1, linkHref.length);
        if (
          linkHrefFile.toLowerCase() === COMPODOC_CURRENT_PAGE_URL.toLowerCase() &&
          link.innerHTML.indexOf('Getting started') == -1 &&
          !dontAddClass &&
          linkHref.toLowerCase().indexOf(localContextInUrl.toLowerCase()) !== -1
        ) {
          link.classList.add('active');
        }
        processLink(link, linkHref);
      }
    }
  }

  function processLogos(entityLogos) {
    for (let i = 0; i < entityLogos.length; i++) {
      let entityLogo = entityLogos[i];
      if (entityLogo) {
        let url = entityLogo.getAttribute('data-src');
        let isDarkMode = window.matchMedia('(prefers-color-scheme: dark)').matches;
        if (isDarkMode && url.indexOf('compodoc') !== -1) {
          url = 'images/compodoc-vectorise-inverted.png';
        }
        if (url.charAt(0) !== '.') {
          let prefix = Array(COMPODOC_CURRENT_PAGE_DEPTH + 2).join('../');
          entityLogo.src = prefix + url;
        }
      }
    }
  }

  function setupMenuCollapse() {
    document.getElementById('btn-menu').addEventListener('click', function () {
      mobileMenu.style.display = menuCollapsed ? 'none' : 'block';
      document.getElementsByTagName('body')[0].style['overflow-y'] = menuCollapsed ? 'auto' : 'hidden';
      menuCollapsed = !menuCollapsed;
    });
  }

  function setupCollapses() {
    let Collapses = document.querySelectorAll('[data-bs-toggle="collapse"]');
    for (let o = 0, cll = Collapses.length; o < cll; o++) {
      let collapse = Collapses[o];
      let options = {};
      options.duration = collapse.getAttribute('data-duration');
      const targetId = collapse.getAttribute('data-bs-target');
      if (targetId !== '') {
        options.parent = collapse;
        const c = new BSN.Collapse(targetId, options);
      }
    }
  }

  // ... Other functions (scroll to active link, dark mode toggle, etc.)

  // Call functions
  setLocalContext();

  let chapterLinks = document.querySelectorAll('[data-type="chapter-link"]');
  processMenuLinks(chapterLinks);

  let entityLinks = document.querySelectorAll('[data-type="entity-link"]');
  processMenuLinks(entityLinks);

  let indexLinks = document.querySelectorAll('[data-type="index-link"]');
  processMenuLinks(indexLinks, true);

  let compodocLogos = document.querySelectorAll('[data-type="compodoc-logo"]');
  let customLogo = document.querySelectorAll('[data-type="custom-logo"]');
  processLogos(compodocLogos);
  processLogos(customLogo);

  setTimeout(function () {
    setupMenuCollapse();
    setupCollapses();
  }, 0);
});
