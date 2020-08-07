var crypto = require('crypto');
var fs = require('fs');

const printHashed = (fileName) => {
	const s = fs.ReadStream(`${process.cwd()}/${fileName}`)
	const shasum = crypto.createHash('sha3-256')	
	s.on('data', (d) => shasum.update(d))
	s.on('end', () => {
		const d = shasum.digest('hex')
		console.log(`${fileName} ${d}`)
	})
}

fs.readdir(process.cwd(), (err, files) => {
	if (err) return console.log(err)
	files.forEach(file => printHashed(file))
})
