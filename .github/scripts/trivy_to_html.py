import json

with open('trivy-report.json', 'r') as f:
    data = json.load(f)

html = ['<html><body><h2>Trivy Scan Report</h2>']

for result in data.get('Results', []):
    html.append(f"<h3>Target: {result.get('Target')}</h3>")
    html.append("<table border='1' cellpadding='5' cellspacing='0'>")
    html.append("<tr><th>Vulnerability ID</th><th>Pkg Name</th><th>Installed Version</th><th>Fixed Version</th><th>Severity</th><th>Title</th></tr>")

    for vuln in result.get('Vulnerabilities', []):
        html.append("<tr>")
        html.append(f"<td>{vuln.get('VulnerabilityID')}</td>")
        html.append(f"<td>{vuln.get('PkgName')}</td>")
        html.append(f"<td>{vuln.get('InstalledVersion')}</td>")
        html.append(f"<td>{vuln.get('FixedVersion', 'N/A')}</td>")
        html.append(f"<td>{vuln.get('Severity')}</td>")
        html.append(f"<td>{vuln.get('Title', '')}</td>")
        html.append("</tr>")

    html.append("</table><br>")

html.append("</body></html>")

with open('email-body.html', 'w') as f:
    f.write('\n'.join(html))
