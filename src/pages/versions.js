/**
 * Copyright (c) 2017-present, Facebook, Inc.
 *
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */

import React from 'react';

import Layout from '@theme/Layout';

// import useDocusaurusContext from '@docusaurus/useDocusaurusContext';
import Link from '@docusaurus/Link';
import useBaseUrl from '@docusaurus/useBaseUrl';

import versions from '../../versions.json';

function Version() {
  // const context = useDocusaurusContext();
  // const { siteConfig = {} } = context;
  const [latestVersion] = versions;
  const pastVersions = versions.filter((version) => version !== latestVersion);
  return (
    <Layout
      permalink="/versions"
      description="Neutron JS - CLI Versions page listing all documented site versions"
    >
      <div className="container margin-vert--xl">
        <h1>Spring VIP - Versions</h1>
        <div className="margin-bottom--lg">
          <h3 id="latest">Latest version (Stable)</h3>
          <p>Here you can find the latest documentation.</p>
          <table>
            <tbody>
              <tr>
                <th>{`v${latestVersion}`}</th>
                <td>
                  <Link to={useBaseUrl('/docs/')}>
                    Documentation
                  </Link>
                </td>
                <td>
                <Link to={`https://gitlab.com/yazilim.vip/projects/community/spring-vip/spring-vip/-/tags/v${latestVersion}`}>
                    Release Notes
                  </Link>
                </td>
              </tr>
            </tbody>
          </table>

        </div>

        <div className="margin-bottom--lg">
          <h3 id="next">Next version (Unreleased)</h3>
          <p>Here you can find the documentation for unreleased version.</p>
          <table>
            <tbody>
              <tr>
                <th>master</th>
                <td>
                  <Link to={useBaseUrl('/docs/next/')}>
                    Documentation
                    </Link>
                </td>
              </tr>
            </tbody>
          </table>
        </div>



        {pastVersions.length > 0 && (
          <div className="margin-bottom--lg">
            <h3 id="archive">Past Versions</h3>
            <p>
              Here you can find documentation for previous versions of
              Docusaurus.
            </p>
            <table>
              <tbody>
                {pastVersions.map((version) => (
                  <tr key={version}>
                    <th>{`v${version}`}</th>
                    <td>
                      <Link
                        to={useBaseUrl(
                          `/docs/${version}/introduction/getting-started`,
                        )}
                      >
                        Documentation
                      </Link>
                    </td>
                    <td>
                        <Link to={`https://gitlab.com/yazilim.vip/projects/community/spring-vip/spring-vip/-/tags/v${version}`}>
                            Release Notes
                        </Link>
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        )}
      </div>
    </Layout>
  );
}

export default Version;