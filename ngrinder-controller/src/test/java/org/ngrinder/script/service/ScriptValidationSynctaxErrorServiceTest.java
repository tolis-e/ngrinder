/* 
 * Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License. 
 */
package org.ngrinder.script.service;

import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.Ignore;
import org.junit.Test;

public class ScriptValidationSynctaxErrorServiceTest {
	public ScriptValidationService scriptValidationService = new ScriptValidationService();

	@Ignore@Test
	public void testSyntaxError() {
		assertThat(scriptValidationService.checkSyntaxErrors("print 'HELL'.."),
				notNullValue());
		assertThat(scriptValidationService.checkSyntaxErrors("print 'HELL'"),
				nullValue());

	}
}
