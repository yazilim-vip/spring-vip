const [latestVersion] = require("./versions.json");

module.exports = {
  title: "Spring VIP",
  tagline:
    "A Java Spring Library that provides helpful generic and utility classes. Some of features provided by library are generic CRUD Service and Rest controllers. See more from documentation",
  url: "https://springvip.yazilim.vip",
  baseUrl: "/",
  onBrokenLinks: "throw",
  favicon: "img/favicon.ico",
  organizationName: "YazılımVIP/maemresen", // Usually your GitHub org/user name.
  projectName: "SpringVIP", // Usually your repo name.
  themeConfig: {
    navbar: {
      title: "Spring VIP",
      logo: {
        alt: "Spring VIP Logo",
        src: "img/yvip_logo_design_vip_only.png",
      },
      items: [
        {
          type: "docsVersionDropdown",
          position: "left",
        },
        {
          to: "docs/",
          activeBasePath: "docs",
          label: "Docs",
          position: "right",
        },
        {
          to: "versions",
          label: `Releases`,
          position: "right",
        },
        {
          href: "https://github.com/yazilim-vip/spring-vip/blob/master/LICENSE",
          className: "header-license-link",
          position: "right",
        },

        {
          href: "https://github.com/yazilim-vip/spring-vip",
          className: "header-stars-link",
          position: "right",
        },
      ],
    },
    footer: {
      style: "dark",
      links: [ 
      ],
      copyright: `Copyright © ${new Date().getFullYear()} Yazilim VIP, Inc. Built with Docusaurus.`,
    },
  },
  presets: [
    [
      "@docusaurus/preset-classic",
      {
        docs: {
          // It is recommended to set document id as docs home page (`docs/` path).
          homePageId: "gettingStarted",
          sidebarPath: require.resolve("./sidebars.js"),
          // Please change this to your repo.
          editUrl:
            "https://github.com/yazilim-vip/spring-vip-wiki/-/tree/master/",
        },
        blog: {
          showReadingTime: true,
          // Please change this to your repo.
          editUrl:
            "https://github.com/yazilim-vip/spring-vip-wiki/-/tree/master/blog",
        },
        theme: {
          customCss: require.resolve("./src/css/custom.css"),
        },
      },
    ],
  ],
};
