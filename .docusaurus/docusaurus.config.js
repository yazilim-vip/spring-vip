export default {
  "title": "Spring VIP",
  "tagline": "The tagline of my site",
  "url": "https://springvip.yazilim.vip",
  "baseUrl": "/",
  "onBrokenLinks": "throw",
  "favicon": "img/favicon.ico",
  "organizationName": "YazılımVIP/maemresen",
  "projectName": "SpringVIP",
  "themeConfig": {
    "navbar": {
      "title": "Spring VIP",
      "logo": {
        "alt": "Spring VIP Logo",
        "src": "img/yvip_logo_design_vip_only.png"
      },
      "items": [
        {
          "type": "docsVersionDropdown",
          "position": "left"
        },
        {
          "to": "docs/",
          "activeBasePath": "docs",
          "label": "Docs",
          "position": "right"
        },
        {
          "to": "blog",
          "label": "Blog",
          "position": "right"
        },
        {
          "to": "versions",
          "label": "Releases",
          "position": "right"
        },
        {
          "href": "https://gitlab.com/yazilim.vip/projects/community/spring-vip/spring-vip",
          "className": "header-github-link",
          "position": "right"
        }
      ],
      "hideOnScroll": false
    },
    "colorMode": {
      "defaultMode": "light",
      "disableSwitch": false,
      "respectPrefersColorScheme": false,
      "switchConfig": {
        "darkIcon": "🌜",
        "darkIconStyle": {},
        "lightIcon": "🌞",
        "lightIconStyle": {}
      }
    },
    "metadatas": []
  },
  "presets": [
    [
      "@docusaurus/preset-classic",
      {
        "docs": {
          "homePageId": "gettingStarted",
          "sidebarPath": "/home/maemresen/workspace/yvip/spring-vip/sidebars.js",
          "editUrl": "https://gitlab.com/yazilim.vip/projects/community/spring-vip/spring-vip-wiki/-/tree/master/"
        },
        "blog": {
          "showReadingTime": true,
          "editUrl": "https://gitlab.com/yazilim.vip/projects/community/spring-vip/spring-vip-wiki/-/tree/master/blog"
        },
        "theme": {
          "customCss": "/home/maemresen/workspace/yvip/spring-vip/src/css/custom.css"
        }
      }
    ]
  ],
  "onDuplicateRoutes": "warn",
  "customFields": {},
  "plugins": [],
  "themes": [],
  "titleDelimiter": "|"
};