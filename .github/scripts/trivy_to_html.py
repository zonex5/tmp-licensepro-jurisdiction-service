import json
from pathlib import Path

with open('trivy-report.json', 'r') as f:
    data = json.load(f)

html = ['<html><body><h2>Trivy Scan Report</h2>']
flag_created = False

results = data.get('Results')
if not results:
    html.append("<p><strong>No vulnerabilities found.</strong></p>")
else:
    found = False
    for result in results:
        vulnerabilities = result.get('Vulnerabilities')
        if not vulnerabilities:
            continue

        found = True
        html.append(f"<h3>Target: {result.get('Target')}</h3>")
        html.append("<table border='1' cellpadding='5' cellspacing='0'>")
        html.append("<tr><th>Vulnerability ID</th><th>Pkg Name</th><th>Installed Version</th><th>Fixed Version</th><th>Severity</th><th>Title</th></tr>")

        for vuln in vulnerabilities:
            html.append("<tr>")
            html.append(f"<td>{vuln.get('VulnerabilityID')}</td>")
            html.append(f"<td>{vuln.get('PkgName')}</td>")
            html.append(f"<td>{vuln.get('InstalledVersion')}</td>")
            html.append(f"<td>{vuln.get('FixedVersion', 'N/A')}</td>")
            html.append(f"<td>{vuln.get('Severity')}</td>")
            html.append(f"<td>{vuln.get('Title', '')}</td>")
            html.append("</tr>")

        html.append("</table><br>")

    if found:
        Path("vulns-found.flag").touch()
    else:
        html.append("<p><strong>No vulnerabilities found.</strong></p>")

html.append("</body></html>")

with open('email-body.html', 'w') as f:
    f.write('\n'.join(html))
