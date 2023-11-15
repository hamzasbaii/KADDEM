document.addEventListener('DOMContentLoaded', function () {
  const mobileMenu = document.getElementById('mobile-menu');
  let localContextInUrl = '';

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


// Unconditional break



    function hasClass(el, cls) {
        return el.className && new RegExp('(\\s|^)' + cls + '(\\s|$)').test(el.className);
    }

    let processLink = function (link, url) {
        if (url.charAt(0) !== '.') {
            let prefix = '';
            switch (COMPODOC_CURRENT_PAGE_DEPTH) {
                case 5:
                    prefix = '../../../../../';
                    break;
                case 4:
                    prefix = '../../../../';
                    break;
                case 3:
                    prefix = '../../../';
                    break;
                case 2:
                    prefix = '../../';
                    break;
                case 1:
                    prefix = '../';
                    break;
                case 0:
                    prefix = './';
                    break;
            }
            link.setAttribute('href', prefix + url);
        }
    };

  let processMenuLinks = function (links, dontAddClass) {
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
    };
    let chapterLinks = document.querySelectorAll('[data-type="chapter-link"]');
    processMenuLinks(chapterLinks);
    let entityLinks = document.querySelectorAll('[data-type="entity-link"]');
    processMenuLinks(entityLinks);
  let indexLinks = document.querySelectorAll('[data-type="index-link"]');
    processMenuLinks(indexLinks, true);
  let compodocLogos = document.querySelectorAll('[data-type="compodoc-logo"]');
  let customLogo = document.querySelectorAll('[data-type="custom-logo"]');
  let processLogos = function (entityLogos) {
        for (let i = 0; i < entityLogos.length; i++) {
          let entityLogo = entityLogos[i];
            if (entityLogo) {
              let url = entityLogo.getAttribute('data-src');
                // Dark mode + logo
                let isDarkMode = window.matchMedia('(prefers-color-scheme: dark)').matches;
                if (isDarkMode && url.indexOf('compodoc') !== -1) {
                    url = 'images/compodoc-vectorise-inverted.png';
                }
                if (url.charAt(0) !== '.') {
                  let prefix = '';
                    switch (COMPODOC_CURRENT_PAGE_DEPTH) {
                        case 5:
                            prefix = '../../../../../';
                            break;
                        case 4:
                            prefix = '../../../../';
                            break;
                        case 3:
                            prefix = '../../../';
                            break;
                        case 2:
                            prefix = '../../';
                            break;
                        case 1:
                            prefix = '../';
                            break;
                        case 0:
                            prefix = './';
                            break;
                    }
                    entityLogo.src = prefix + url;
                }
            }
        }
    };
    processLogos(compodocLogos);
    processLogos(customLogo);

    setTimeout(function () {
        document.getElementById('btn-menu').addEventListener('click', function () {
            if (menuCollapsed) {
                mobileMenu.style.display = 'none';
            } else {
                mobileMenu.style.display = 'block';
                document.getElementsByTagName('body')[0].style['overflow-y'] = 'hidden';
            }
            menuCollapsed = !menuCollapsed;
        });

        /**
         * Native bootstrap doesn't wait DOMContentLoaded event to start his job, re do it here
         */
        let Collapses = document.querySelectorAll('[data-bs-toggle="collapse"]');
        for (let o = 0, cll = Collapses.length; o < cll; o++) {
          let collapse = Collapses[o],
                options = {};
            options.duration = collapse.getAttribute('data-duration');
            const targetId = collapse.getAttribute('data-bs-target');
            if (targetId !== '') {
                options.parent = collapse;
                const c = new BSN.Collapse(targetId, options);
            }
        }

        // collapse menu
      let classnameMenuToggler = document.getElementsByClassName('menu-toggler'),
            faAngleUpClass = 'ion-ios-arrow-up',
            faAngleDownClass = 'ion-ios-arrow-down',
            toggleItemMenu = function (e) {
              let element = $(e.target),
                    parent = element[0].parentNode,
                    parentLink,
                    elementIconChild;
                if (parent) {
                    if (!$(parent).hasClass('linked')) {
                        e.preventDefault();
                    } else {
                        parentLink = parent.parentNode;
                        if (parentLink && element.hasClass('link-name')) {
                            $(parentLink).trigger('click');
                        }
                    }
                    elementIconChild = parent.getElementsByClassName(faAngleUpClass)[0];
                    if (!elementIconChild) {
                        elementIconChild = parent.getElementsByClassName(faAngleDownClass)[0];
                    }
                    if (elementIconChild) {
                        elementIconChild = $(elementIconChild);
                        if (elementIconChild.hasClass(faAngleUpClass)) {
                            elementIconChild.addClass(faAngleDownClass);
                            elementIconChild.removeClass(faAngleUpClass);
                        } else {
                            elementIconChild.addClass(faAngleUpClass);
                            elementIconChild.removeClass(faAngleDownClass);
                        }
                    }
                }
            };

        for (let i = 0; i < classnameMenuToggler.length; i++) {
            classnameMenuToggler[i].addEventListener('click', toggleItemMenu, false);
        }

        // Scroll to active link
        let menus = document.querySelectorAll('.menu'),
            i = 0,
            len = menus.length,
            activeMenu,
            activeMenuClass,
            activeLink;

        for (i; i < len; i++) {
            if (getComputedStyle(menus[i]).display != 'none') {
                activeMenu = menus[i];
                activeMenuClass = activeMenu.getAttribute('class').split(' ')[0];
            }
        }

        if (activeMenu) {
            activeLink = document.querySelector('.' + activeMenuClass + ' .active');
            if (activeLink) {
              let linkType = activeLink.getAttribute('data-type');
              let linkContext = activeLink.getAttribute('data-context');
                if (linkType === 'entity-link') {
                  let parentLi = activeLink.parentNode,
                        parentUl,
                        parentChapterMenu;
                    if (parentLi) {
                        parentUl = parentLi.parentNode;
                        if (parentUl) {
                            parentChapterMenu = parentUl.parentNode;
                            if (parentChapterMenu) {
                                let toggler = parentChapterMenu.querySelector('.menu-toggler'),
                                    elementIconChild =
                                        toggler.getElementsByClassName(faAngleUpClass)[0];
                                if (toggler && !elementIconChild) {
                                    toggler.click();
                                }
                            }
                        }
                    }
                    if (linkContext && linkContext === 'sub-entity') {
                        // Toggle also the master parent menu
                        let linkContextId = activeLink.getAttribute('data-context-id');
                        let toggler = activeMenu.querySelector(
                            '.chapter.' + linkContextId + ' a .menu-toggler'
                        );
                        if (toggler) {
                            toggler.click();
                        }
                        if (linkContextId === 'additional') {
                            let mainToggler = activeMenu.querySelector(
                                '.chapter.' + linkContextId + ' div.menu-toggler'
                            );
                            if (mainToggler) {
                                mainToggler.click();
                            }
                        }
                    }
                } else if (linkType === 'chapter-link') {
                    let linkContextId = activeLink.getAttribute('data-context-id');
                    let toggler = activeLink.querySelector('.menu-toggler');
                    if (toggler) {
                        toggler.click();
                    }
                    if (linkContextId === 'additional') {
                        let mainToggler = activeMenu.querySelector(
                            '.chapter.' + linkContextId + ' div.menu-toggler'
                        );
                        if (mainToggler) {
                            mainToggler.click();
                        }
                    }
                }
                setTimeout(function () {
                    activeMenu.scrollTop = activeLink.offsetTop;
                    if (
                        activeLink.innerHTML.toLowerCase().indexOf('readme') != -1 ||
                        activeLink.innerHTML.toLowerCase().indexOf('overview') != -1
                    ) {
                        activeMenu.scrollTop = 0;
                    }
                }, 300);
            }
        }
        // Dark mode toggle button
      let useDark = window.matchMedia('(prefers-color-scheme: dark)');
      let darkModeState = useDark.matches;
      let $darkModeToggleSwitchers = document.querySelectorAll('.dark-mode-switch input');
      let $darkModeToggles = document.querySelectorAll('.dark-mode-switch');
      let darkModeStateLocal = localStorage.getItem('compodoc_darkmode-state');

        function checkToggle(check) {
            for (let i = 0; i < $darkModeToggleSwitchers.length; i++) {
                $darkModeToggleSwitchers[i].checked = check;
            }
        }

        function toggleDarkMode(state) {
            if (window.localStorage) {
                localStorage.setItem('compodoc_darkmode-state', state);
            }

            checkToggle(state);

            const hasClass = document.body.classList.contains('dark');

            if (state) {
                for (let i = 0; i < $darkModeToggles.length; i++) {
                    $darkModeToggles[i].classList.add('dark');
                }
                if (!hasClass) {
                    document.body.classList.add('dark');
                }
            } else {
                for (let i = 0; i < $darkModeToggles.length; i++) {
                    $darkModeToggles[i].classList.remove('dark');
                }
                if (hasClass) {
                    document.body.classList.remove('dark');
                }
            }
        }

        useDark.addEventListener('change', function (evt) {
            toggleDarkMode(evt.matches);
        });
        if (darkModeStateLocal) {
            darkModeState = darkModeStateLocal === 'true';
        }
        toggleDarkMode(darkModeState);

        if ($darkModeToggles.length > 0) {
            for (let i = 0; i < $darkModeToggleSwitchers.length; i++) {
                $darkModeToggleSwitchers[i].addEventListener('change', function (event) {
                    darkModeState = !darkModeState;
                    toggleDarkMode(darkModeState);
                });
            }
        }
    }, 0);
});
